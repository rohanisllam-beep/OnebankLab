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
import androidx.compose.material.icons.filled.ElectricBolt
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.SwapHoriz
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
fun Concept03AuroraFlow(
    state: BankUiState,
    viewModel: BankAppViewModel,
    modifier: Modifier = Modifier
) {
    val language = state.language

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFF7FDF9),
        topBar = {
            ConceptTopBar(
                currentConcept = ConceptId.AURORA_FLOW,
                language = language,
                isBalanceHidden = state.isBalanceHidden,
                onBackToShowroom = { viewModel.openShowroom() },
                onSelectConcept = { viewModel.selectConcept(it) },
                onToggleLanguage = { viewModel.toggleLanguage() },
                onTogglePrivacy = { viewModel.toggleBalancePrivacy() }
            )
        },
        bottomBar = {
            AuroraFlowBottomNav(
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
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFE6FFFA),
                            Color(0xFFF0FDF4),
                            Color.White
                        )
                    )
                )
        ) {
            when (state.currentScreen) {
                ScreenRoute.HOME -> AuroraFlowHomeDashboard(state, viewModel)
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
private fun AuroraFlowHomeDashboard(
    state: BankUiState,
    viewModel: BankAppViewModel
) {
    val language = state.language
    val account = state.selectedAccount

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Aurora Brand Greeting
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
                        text = "ONE BANK AURORA",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0D9488),
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = Strings.tr("user_name", language),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFF064E3B)
                    )
                }
                OneBankBrandLogo(iconSize = 32.dp, showText = true, accentColor = Color(0xFF10B981))
            }
        }

        // Luminous Aurora Wave Balance Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(GlassEffects.auroraFlowModifier(RoundedCornerShape(26.dp), 8.dp))
                    .padding(22.dp)
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = if (language == Language.BN) account.nameBn else account.nameEn,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F766E)
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(8.dp))
                                .background(Color(0xFFCCFBF1))
                                .padding(horizontal = 8.dp, vertical = 3.dp)
                        ) {
                            Text(
                                text = "NPSB LIVE",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F766E)
                            )
                        }
                    }

                    Column {
                        Text(
                            text = Strings.tr("available_balance", language),
                            fontSize = 12.sp,
                            color = Color(0xFF475569)
                        )
                        Text(
                            text = if (state.isBalanceHidden) "••••••••" else Strings.formatCurrency(account.balance, language),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF064E3B)
                        )
                    }

                    // Floating aurora badges
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column {
                            Text(
                                text = Strings.tr("credit_available", language),
                                fontSize = 10.sp,
                                color = Color(0xFF64748B)
                            )
                            Text(
                                text = if (state.isBalanceHidden) "••••" else Strings.formatCurrency(214500.0, language),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0D9488)
                            )
                        }
                        Column(horizontalAlignment = Alignment.End) {
                            Text(
                                text = Strings.tr("total_deposits", language),
                                fontSize = 10.sp,
                                color = Color(0xFF64748B)
                            )
                            Text(
                                text = if (state.isBalanceHidden) "••••" else Strings.formatCurrency(520000.0, language),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF16A34A)
                            )
                        }
                    }
                }
            }
        }

        // Fluid Action Bubbles
        item {
            Text(
                text = Strings.tr("quick_actions", language),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF064E3B)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                val actions = listOf(
                    Triple(Strings.tr("action_send", language), Icons.AutoMirrored.Filled.Send, ScreenRoute.TRANSFERS),
                    Triple(Strings.tr("action_recharge", language), Icons.Default.PhoneAndroid, ScreenRoute.TRANSFERS),
                    Triple(Strings.tr("action_bills", language), Icons.Default.ElectricBolt, ScreenRoute.TRANSFERS),
                    Triple(Strings.tr("action_cards", language), Icons.Default.CreditCard, ScreenRoute.CARDS)
                )

                actions.forEach { (label, icon, route) ->
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .clickable { viewModel.navigateTo(route) }
                            .padding(4.dp),
                        verticalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            Color(0xFFE0F7FA),
                                            Color(0xFFA7F3D0)
                                        )
                                    )
                                )
                                .border(1.dp, Color(0xFF10B981).copy(alpha = 0.5f), CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = icon,
                                contentDescription = label,
                                tint = Color(0xFF064E3B),
                                modifier = Modifier.size(22.dp)
                            )
                        }
                        Text(
                            text = label,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF064E3B)
                        )
                    }
                }
            }
        }

        // Recent Activity
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
                    color = Color(0xFF064E3B)
                )
                Text(
                    text = Strings.tr("see_all", language),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D9488),
                    modifier = Modifier.clickable { viewModel.navigateTo(ScreenRoute.ACCOUNTS) }
                )
            }
        }

        items(state.transactions.take(4)) { tx ->
            TransactionRowCard(
                transaction = tx,
                language = language,
                isBalanceHidden = state.isBalanceHidden,
                onClick = { viewModel.showTransactionDetail(tx) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Composable
private fun AuroraFlowBottomNav(
    currentScreen: ScreenRoute,
    language: Language,
    onNavigate: (ScreenRoute) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(24.dp),
        color = Color(0xFFFFFFFF).copy(alpha = 0.95f),
        shadowElevation = 6.dp
    ) {
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
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (isSelected) Color(0xFF10B981).copy(alpha = 0.18f) else Color.Transparent)
                        .clickable { onNavigate(route) }
                        .padding(horizontal = 12.dp, vertical = 6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = title,
                            tint = if (isSelected) Color(0xFF0D9488) else Color(0xFF64748B),
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = title,
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color(0xFF0D9488) else Color(0xFF64748B)
                        )
                    }
                }
            }
        }
    }
}
