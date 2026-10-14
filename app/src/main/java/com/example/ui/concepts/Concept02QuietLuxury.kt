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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
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
import com.example.ui.screens.TransactionRowCard
import com.example.ui.screens.TransfersScreen

@Composable
fun Concept02QuietLuxury(
    state: BankUiState,
    viewModel: BankAppViewModel,
    modifier: Modifier = Modifier
) {
    val language = state.language

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFFBF9F5),
        topBar = {
            ConceptTopBar(
                currentConcept = ConceptId.QUIET_LUXURY,
                language = language,
                isBalanceHidden = state.isBalanceHidden,
                onBackToShowroom = { viewModel.openShowroom() },
                onSelectConcept = { viewModel.selectConcept(it) },
                onToggleLanguage = { viewModel.toggleLanguage() },
                onTogglePrivacy = { viewModel.toggleBalancePrivacy() }
            )
        },
        bottomBar = {
            QuietLuxuryBottomNav(
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
                .background(Color(0xFFFBF9F5))
        ) {
            when (state.currentScreen) {
                ScreenRoute.HOME -> QuietLuxuryHomeDashboard(state, viewModel)
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
private fun QuietLuxuryHomeDashboard(
    state: BankUiState,
    viewModel: BankAppViewModel
) {
    val language = state.language
    val account = state.selectedAccount

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Minimal brand header
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "ONE BANK PRIVATE",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFC5A059),
                        letterSpacing = 2.sp
                    )
                    Text(
                        text = Strings.tr("user_name", language),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Light,
                        color = Color(0xFF1F2421),
                        fontFamily = FontFamily.Serif
                    )
                }
                OneBankBrandLogo(iconSize = 30.dp, showText = false, accentColor = Color(0xFFC5A059))
            }
        }

        // Quiet Luxury Typographic Balance Area (Generous whitespace & champagne hairline)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(GlassEffects.quietLuxuryModifier(RoundedCornerShape(16.dp), 3.dp))
                    .padding(24.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = if (language == Language.BN) account.nameBn else account.nameEn,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF78716C),
                        letterSpacing = 1.sp
                    )

                    Text(
                        text = if (state.isBalanceHidden) "••••••••" else Strings.formatCurrency(account.balance, language),
                        fontSize = 34.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color(0xFF1F2421),
                        fontFamily = FontFamily.Serif
                    )

                    // Fine gold accent hairline
                    Box(
                        modifier = Modifier
                            .size(width = 48.dp, height = 1.5.dp)
                            .background(Color(0xFFC5A059))
                    )

                    Text(
                        text = "${Strings.tr("account_num_label", language)} ${account.accountNumber}",
                        fontSize = 11.sp,
                        color = Color(0xFFA8A29E)
                    )
                }
            }
        }

        // Curated Quiet Actions
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                val quietActions = listOf(
                    Triple(Strings.tr("action_send", language), Icons.AutoMirrored.Filled.Send, ScreenRoute.TRANSFERS),
                    Triple(Strings.tr("action_recharge", language), Icons.Default.PhoneAndroid, ScreenRoute.TRANSFERS),
                    Triple(Strings.tr("action_cards", language), Icons.Default.CreditCard, ScreenRoute.CARDS)
                )

                quietActions.forEach { (title, icon, route) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { viewModel.navigateTo(route) }
                            .padding(horizontal = 12.dp, vertical = 6.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF3EFE6))
                                .border(0.8.dp, Color(0xFFD4AF37).copy(alpha = 0.4f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = title,
                                tint = Color(0xFF1F2421),
                                modifier = Modifier.size(20.dp)
                            )
                        }
                        Text(
                            text = title,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF44403C)
                        )
                    }
                }
            }
        }

        // Ledger Style Recent Transactions
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Strings.tr("recent_transactions", language),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = FontFamily.Serif,
                    color = Color(0xFF1F2421)
                )
                Text(
                    text = Strings.tr("see_all", language),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC5A059),
                    modifier = Modifier.clickable { viewModel.navigateTo(ScreenRoute.ACCOUNTS) }
                )
            }
        }

        items(state.transactions.take(4)) { tx ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { viewModel.showTransactionDetail(tx) },
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (language == Language.BN) tx.titleBn else tx.titleEn,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF1F2421)
                        )
                        Text(
                            text = Strings.formatDate(if (language == Language.BN) tx.dateBn else tx.dateEn, language),
                            fontSize = 10.sp,
                            color = Color(0xFF78716C)
                        )
                    }

                    val prefix = if (tx.isCredit) "+" else "-"
                    Text(
                        text = if (state.isBalanceHidden) "••••" else prefix + Strings.formatCurrency(tx.amount, language),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (tx.isCredit) Color(0xFF16A34A) else Color(0xFF1F2421),
                        fontFamily = FontFamily.Serif
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
private fun QuietLuxuryBottomNav(
    currentScreen: ScreenRoute,
    language: Language,
    onNavigate: (ScreenRoute) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = Color(0xFFFCFBF8),
        shadowElevation = 4.dp
    ) {
        Column {
            HorizontalDivider(color = Color(0xFFE7E5E4), thickness = 0.8.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp, horizontal = 16.dp),
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
                            .padding(horizontal = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = title,
                            tint = if (isSelected) Color(0xFFC5A059) else Color(0xFFA8A29E),
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = title,
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                            color = if (isSelected) Color(0xFFC5A059) else Color(0xFFA8A29E)
                        )
                    }
                }
            }
        }
    }
}
