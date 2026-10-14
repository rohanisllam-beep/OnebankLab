package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDownward
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.LocalAtm
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.TrendingUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.Strings
import com.example.model.BankAccount
import com.example.model.Language
import com.example.model.Transaction
import com.example.state.BankUiState

@Composable
fun AccountsScreen(
    state: BankUiState,
    onSelectAccount: (BankAccount) -> Unit,
    onSearchChange: (String) -> Unit,
    onFilterChange: (String) -> Unit,
    onTransactionClick: (Transaction) -> Unit,
    modifier: Modifier = Modifier
) {
    val language = state.language
    val filteredTransactions = state.transactions.filter { tx ->
        val matchesQuery = state.searchQuery.isBlank() ||
                tx.titleEn.contains(state.searchQuery, ignoreCase = true) ||
                tx.titleBn.contains(state.searchQuery, ignoreCase = true) ||
                tx.subtitleEn.contains(state.searchQuery, ignoreCase = true) ||
                tx.referenceNumber.contains(state.searchQuery, ignoreCase = true)

        val matchesFilter = when (state.selectedFilter) {
            "IN" -> tx.isCredit
            "OUT" -> !tx.isCredit
            else -> true
        }
        matchesQuery && matchesFilter
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = Strings.tr("nav_accounts", language),
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1E1B1B)
            )
            Text(
                text = if (language == Language.BN) "আপনার সক্রিয় হিসাব ও লেনদেনের ইতিহাস" else "Your active accounts & complete transaction history",
                fontSize = 13.sp,
                color = Color(0xFF757575)
            )
        }

        // Account Switcher Cards Carousel
        item {
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(state.accounts) { account ->
                    val isSelected = account.id == state.selectedAccount.id
                    Card(
                        modifier = Modifier
                            .size(width = 230.dp, height = 115.dp)
                            .clickable { onSelectAccount(account) },
                        shape = RoundedCornerShape(18.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) Color(0xFFFFF8F8) else Color.White
                        ),
                        elevation = CardDefaults.cardElevation(defaultElevation = if (isSelected) 3.dp else 1.5.dp),
                        border = androidx.compose.foundation.BorderStroke(
                            width = if (isSelected) 1.5.dp else 1.dp,
                            color = if (isSelected) Color(0xFFE82E2E) else Color(0xFFF3ECEC)
                        )
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(14.dp),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = if (language == Language.BN) account.typeBn else account.typeEn,
                                    fontSize = 11.sp,
                                    color = Color(0xFF757575)
                                )
                                if (account.isPrimary) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(6.dp))
                                            .background(Color(0xFFF5F3FA))
                                            .border(0.5.dp, Color(0xFFEAE7F2), RoundedCornerShape(6.dp))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = if (language == Language.BN) "মূল" else "Primary",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFFE82E2E)
                                        )
                                    }
                                }
                            }

                            Column {
                                Text(
                                    text = if (language == Language.BN) account.nameBn else account.nameEn,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E1B1B)
                                )
                                Text(
                                    text = if (state.isBalanceHidden) "••••••••" else Strings.formatCurrency(account.balance, language),
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.ExtraBold,
                                    color = Color(0xFFE82E2E)
                                )
                            }
                        }
                    }
                }
            }
        }

        // Search Input
        item {
            OutlinedTextField(
                value = state.searchQuery,
                onValueChange = onSearchChange,
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = Strings.tr("search_transactions", language),
                        fontSize = 13.sp,
                        color = Color(0xFF9E9E9E)
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color(0xFFE82E2E)
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFE82E2E),
                    unfocusedBorderColor = Color(0xFFF3ECEC),
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )
        }

        // Filter Pills (All / Money In / Money Out)
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                listOf(
                    "ALL" to Strings.tr("filter_all", language),
                    "IN" to Strings.tr("filter_in", language),
                    "OUT" to Strings.tr("filter_out", language)
                ).forEach { (key, label) ->
                    val isSelected = state.selectedFilter == key
                    Surface(
                        modifier = Modifier
                            .clip(RoundedCornerShape(12.dp))
                            .clickable { onFilterChange(key) },
                        color = if (isSelected) Color(0xFFE82E2E) else Color.White,
                        border = androidx.compose.foundation.BorderStroke(1.dp, if (isSelected) Color(0xFFE82E2E) else Color(0xFFF3ECEC)),
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Text(
                            text = label,
                            fontSize = 12.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color.White else Color(0xFF616161),
                            modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp)
                        )
                    }
                }
            }
        }

        // Transactions count
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Strings.tr("recent_transactions", language),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E293B)
                )
                Text(
                    text = "${filteredTransactions.size} " + if (language == Language.BN) "লেনদেন" else "items",
                    fontSize = 12.sp,
                    color = Color(0xFF64748B)
                )
            }
        }

        // Transaction Rows
        if (filteredTransactions.isEmpty()) {
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = Strings.tr("no_transactions_found", language),
                        fontSize = 13.sp,
                        color = Color(0xFF94A3B8)
                    )
                }
            }
        } else {
            items(filteredTransactions) { tx ->
                TransactionRowCard(
                    transaction = tx,
                    language = language,
                    isBalanceHidden = state.isBalanceHidden,
                    onClick = { onTransactionClick(tx) }
                )
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun TransactionRowCard(
    transaction: Transaction,
    language: Language,
    isBalanceHidden: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            // Category Icon Badge
            val (icon, bg, fg) = getCategoryStyling(transaction.category, transaction.isCredit)
            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(CircleShape)
                    .background(bg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = transaction.category,
                    tint = fg,
                    modifier = Modifier.size(20.dp)
                )
            }

            // Title & Date
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (language == Language.BN) transaction.titleBn else transaction.titleEn,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF0F172A)
                )
                Text(
                    text = if (language == Language.BN) transaction.subtitleBn else transaction.subtitleEn,
                    fontSize = 11.sp,
                    color = Color(0xFF64748B),
                    maxLines = 1
                )
                Text(
                    text = Strings.formatDate(if (language == Language.BN) transaction.dateBn else transaction.dateEn, language),
                    fontSize = 10.sp,
                    color = Color(0xFF94A3B8)
                )
            }

            // Amount
            Column(horizontalAlignment = Alignment.End) {
                val prefix = if (transaction.isCredit) "+" else "-"
                val amountText = if (isBalanceHidden) "••••" else prefix + Strings.formatCurrency(transaction.amount, language)
                Text(
                    text = amountText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (transaction.isCredit) Color(0xFF16A34A) else Color(0xFF0F172A)
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(if (transaction.isCredit) Color(0xFFDCFCE7) else Color(0xFFF1F5F9))
                        .padding(horizontal = 6.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = if (language == Language.BN) transaction.statusBn else transaction.statusEn,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (transaction.isCredit) Color(0xFF15803D) else Color(0xFF475569)
                    )
                }
            }
        }
    }
}

fun getCategoryStyling(category: String, isCredit: Boolean): Triple<ImageVector, Color, Color> {
    return when (category) {
        "SALARY", "INTEREST" -> Triple(Icons.Default.TrendingUp, Color(0xFFDCFCE7), Color(0xFF16A34A))
        "RECHARGE" -> Triple(Icons.Default.PhoneAndroid, Color(0xFFE0F2FE), Color(0xFF0284C7))
        "BILL" -> Triple(Icons.Default.ElectricBolt, Color(0xFFFEF3C7), Color(0xFFD97706))
        "CARD" -> Triple(Icons.Default.CreditCard, Color(0xFFF3E8FF), Color(0xFF7E22CE))
        "ATM" -> Triple(Icons.Default.LocalAtm, Color(0xFFF5F3FA), Color(0xFFE82E2E))
        "TRANSFER" -> if (isCredit) {
            Triple(Icons.Default.ArrowDownward, Color(0xFFDCFCE7), Color(0xFF16A34A))
        } else {
            Triple(Icons.Default.ArrowUpward, Color(0xFFF5F3FA), Color(0xFFE82E2E))
        }
        else -> Triple(Icons.Default.Receipt, Color(0xFFF1F5F9), Color(0xFF475569))
    }
}
