package com.example.ui.concepts

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
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.Strings
import com.example.model.ConceptId
import com.example.model.Language
import com.example.model.ScreenRoute
import com.example.state.BankAppViewModel
import com.example.state.BankUiState
import com.example.ui.components.ConceptTopBar
import com.example.ui.components.GlassEffects
import com.example.ui.components.OneBankBrandLogo
import com.example.ui.screens.AccountsScreen
import com.example.ui.screens.CardsScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.TransfersScreen

@Composable
fun Concept04PrecisionFinancial(
    state: BankUiState,
    viewModel: BankAppViewModel,
    modifier: Modifier = Modifier
) {
    val language = state.language

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF1F5F9),
        topBar = {
            ConceptTopBar(
                currentConcept = ConceptId.PRECISION_FINANCIAL,
                language = language,
                isBalanceHidden = state.isBalanceHidden,
                onBackToShowroom = { viewModel.openShowroom() },
                onSelectConcept = { viewModel.selectConcept(it) },
                onToggleLanguage = { viewModel.toggleLanguage() },
                onTogglePrivacy = { viewModel.toggleBalancePrivacy() }
            )
        },
        bottomBar = {
            PrecisionBottomNav(
                currentScreen = state.currentScreen,
                language = language,
                onNavigate = { viewModel.navigateTo(it) }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF1F5F9))
        ) {
            when (state.currentScreen) {
                ScreenRoute.HOME -> PrecisionHomeDashboard(state, viewModel)
                ScreenRoute.ACCOUNTS -> AccountsScreen(
                    state = state,
                    onSelectAccount = { viewModel.selectAccount(it) },
                    onSearchChange = { viewModel.setSearchQuery(it) },
                    onFilterChange = { viewModel.setFilter(it) },
                    onTransactionClick = { viewModel.showTransactionDetail(it) }
                )
                ScreenRoute.TRANSFERS -> TransfersScreen(state, viewModel)
                ScreenRoute.CARDS -> CardsScreen(state, viewModel)
                ScreenRoute.PROFILE -> ProfileScreen(state, viewModel)
            }
        }
    }
}

@Composable
private fun PrecisionHomeDashboard(
    state: BankUiState,
    viewModel: BankAppViewModel
) {
    val language = state.language
    val account = state.selectedAccount

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // High Precision Top Header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "TERMINAL: OBL-DESK-904",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0047AB),
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = Strings.tr("user_name", language),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF0F172A)
                    )
                }
                OneBankBrandLogo(iconSize = 30.dp, showText = true, accentColor = Color(0xFF0047AB))
            }
        }

        // Bento Dual-Metric Balance Architecture
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(GlassEffects.precisionModifier(RoundedCornerShape(14.dp), 3.dp))
                    .padding(16.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "${if (language == Language.BN) account.nameBn else account.nameEn} • ${account.accountNumber}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0047AB),
                            fontFamily = FontFamily.Monospace
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(4.dp))
                                .background(Color(0xFFE2E8F0))
                                .padding(horizontal = 6.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "CORE-BANKING OK",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF334155),
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }

                    // Available & Ledger Split
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(12.dp)
                    ) {
                        Column(modifier = Modifier.weight(1.2f)) {
                            Text(
                                text = Strings.tr("available_balance", language),
                                fontSize = 11.sp,
                                color = Color(0xFF64748B)
                            )
                            Text(
                                text = if (state.isBalanceHidden) "••••••••" else Strings.formatCurrency(account.balance, language),
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Black,
                                color = Color(0xFF0F172A),
                                fontFamily = FontFamily.Monospace
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(0.8f)
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFF8FAFC))
                                .border(1.dp, Color(0xFFE2E8F0), RoundedCornerShape(8.dp))
                                .padding(8.dp)
                        ) {
                            Text(
                                text = if (language == Language.BN) "অন-হোল্ড / ক্লিয়ারিং" else "Uncleared / Hold",
                                fontSize = 10.sp,
                                color = Color(0xFF64748B)
                            )
                            Text(
                                text = if (state.isBalanceHidden) "••••" else Strings.formatCurrency(0.0, language),
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF16A34A),
                                fontFamily = FontFamily.Monospace
                            )
                        }
                    }
                }
            }
        }

        // Modular 4-Button Grid Matrix
        item {
            Text(
                text = Strings.tr("quick_actions", language),
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF334155),
                letterSpacing = 0.5.sp
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val gridActions = listOf(
                    Triple(Strings.tr("action_send", language), Icons.AutoMirrored.Filled.Send, ScreenRoute.TRANSFERS),
                    Triple(Strings.tr("action_recharge", language), Icons.Default.PhoneAndroid, ScreenRoute.TRANSFERS),
                    Triple(Strings.tr("action_bills", language), Icons.Default.ElectricBolt, ScreenRoute.TRANSFERS),
                    Triple(Strings.tr("action_cards", language), Icons.Default.CreditCard, ScreenRoute.CARDS)
                )

                gridActions.forEach { (title, icon, route) ->
                    Surface(
                        modifier = Modifier
                            .weight(1f)
                            .clip(RoundedCornerShape(10.dp))
                            .clickable { viewModel.navigateTo(route) },
                        shape = RoundedCornerShape(10.dp),
                        color = Color.White,
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
                    ) {
                        Column(
                            modifier = Modifier.padding(vertical = 12.dp, horizontal = 4.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = title,
                                tint = Color(0xFF0047AB),
                                modifier = Modifier.size(18.dp)
                            )
                            Text(
                                text = title,
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E293B)
                            )
                        }
                    }
                }
            }
        }

        // High-Density Transaction Ledger
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Strings.tr("recent_transactions", language),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF334155)
                )
                Text(
                    text = Strings.tr("see_all", language),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0047AB),
                    modifier = Modifier.clickable { viewModel.navigateTo(ScreenRoute.ACCOUNTS) }
                )
            }
        }

        items(state.transactions.take(5)) { tx ->
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.showTransactionDetail(tx) },
                shape = RoundedCornerShape(8.dp),
                color = Color.White,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0))
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(3.dp))
                                    .background(if (tx.isCredit) Color(0xFFDCFCE7) else Color(0xFFF1F5F9))
                                    .padding(horizontal = 4.dp, vertical = 1.dp)
                            ) {
                                Text(
                                    text = tx.id,
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if (tx.isCredit) Color(0xFF16A34A) else Color(0xFF475569),
                                    fontFamily = FontFamily.Monospace
                                )
                            }
                            Text(
                                text = if (language == Language.BN) tx.titleBn else tx.titleEn,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F172A)
                            )
                        }
                        Text(
                            text = Strings.formatDate(if (language == Language.BN) tx.dateBn else tx.dateEn, language),
                            fontSize = 10.sp,
                            color = Color(0xFF64748B)
                        )
                    }

                    val prefix = if (tx.isCredit) "+" else "-"
                    Text(
                        text = if (state.isBalanceHidden) "••••" else prefix + Strings.formatCurrency(tx.amount, language),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (tx.isCredit) Color(0xFF16A34A) else Color(0xFF0F172A),
                        fontFamily = FontFamily.Monospace
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun PrecisionBottomNav(
    currentScreen: ScreenRoute,
    language: Language,
    onNavigate: (ScreenRoute) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = Color.White,
        shadowElevation = 6.dp
    ) {
        Column {
            HorizontalDivider(color = Color(0xFFCBD5E1), thickness = 1.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 12.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val navItems = listOf(
                    Triple(ScreenRoute.HOME, Strings.tr("nav_home", language), Icons.Default.Home),
                    Triple(ScreenRoute.ACCOUNTS, Strings.tr("nav_accounts", language), Icons.Default.ReceiptLong),
                    Triple(ScreenRoute.TRANSFERS, Strings.tr("nav_transfers", language), Icons.Default.SwapHoriz),
                    Triple(ScreenRoute.CARDS, Strings.tr("nav_cards", language), Icons.Default.CreditCard),
                    Triple(ScreenRoute.PROFILE, Strings.tr("nav_profile", language), Icons.Default.Person)
                )

                navItems.forEach { (route, title, icon) ->
                    val isSelected = currentScreen == route
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { onNavigate(route) }
                            .padding(horizontal = 10.dp, vertical = 2.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = title,
                            tint = if (isSelected) Color(0xFF0047AB) else Color(0xFF64748B),
                            modifier = Modifier.size(18.dp)
                        )
                        Text(
                            text = title,
                            fontSize = 9.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color(0xFF0047AB) else Color(0xFF64748B),
                            fontFamily = FontFamily.Monospace
                        )
                    }
                }
            }
        }
    }
}
