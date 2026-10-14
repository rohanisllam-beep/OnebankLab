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
fun Concept06CrystalEdge(
    state: BankUiState,
    viewModel: BankAppViewModel,
    modifier: Modifier = Modifier
) {
    val language = state.language

    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = Color(0xFFFBFBFE),
        topBar = {
            ConceptTopBar(
                currentConcept = ConceptId.CRYSTAL_EDGE,
                language = language,
                isBalanceHidden = state.isBalanceHidden,
                onBackToShowroom = { viewModel.openShowroom() },
                onSelectConcept = { viewModel.selectConcept(it) },
                onToggleLanguage = { viewModel.toggleLanguage() },
                onTogglePrivacy = { viewModel.toggleBalancePrivacy() }
            )
        },
        bottomBar = {
            CrystalEdgeBottomNav(
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
                            Color(0xFFEEF2FF),
                            Color(0xFFFBFBFE),
                            Color.White
                        )
                    )
                )
        ) {
            when (state.currentScreen) {
                ScreenRoute.HOME -> CrystalEdgeHomeDashboard(state, viewModel)
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
private fun CrystalEdgeHomeDashboard(
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
        // High-Tech Crystal Header
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
                        text = "ONE CRYSTAL ARCHITECTURE",
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Black,
                        color = Color(0xFF6366F1),
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = Strings.tr("user_name", language),
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1E1B4B)
                    )
                }
                OneBankBrandLogo(iconSize = 32.dp, showText = true, accentColor = Color(0xFF6366F1))
            }
        }

        // Geometric Crystal Balance Panel (Faceted violet-blue border & prisms)
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .then(GlassEffects.crystalEdgeModifier(RoundedCornerShape(20.dp), 8.dp))
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
                            color = Color(0xFF4338CA)
                        )
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(6.dp))
                                .background(Color(0xFFE0E7FF))
                                .border(1.dp, Color(0xFF818CF8).copy(alpha = 0.5f), RoundedCornerShape(6.dp))
                                .padding(horizontal = 8.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "PRISM ENCRYPTED",
                                fontSize = 9.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF4338CA)
                            )
                        }
                    }

                    Column {
                        Text(
                            text = Strings.tr("available_balance", language),
                            fontSize = 12.sp,
                            color = Color(0xFF64748B)
                        )
                        Text(
                            text = if (state.isBalanceHidden) "••••••••" else Strings.formatCurrency(account.balance, language),
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF1E1B4B)
                        )
                    }

                    // Prismatic Edge Highlight line
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.5.dp)
                            .background(
                                brush = Brush.horizontalGradient(
                                    colors = listOf(
                                        Color(0xFF6366F1),
                                        Color(0xFFA855F7),
                                        Color(0xFFEC4899),
                                        Color.Transparent
                                    )
                                )
                            )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = account.accountNumber,
                            fontSize = 11.sp,
                            color = Color(0xFF64748B)
                        )
                        Text(
                            text = "VISA Platinum Linked",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color(0xFF4338CA)
                        )
                    }
                }
            }
        }

        // Diamond / Crystalline Action Chips
        item {
            Text(
                text = Strings.tr("quick_actions", language),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E1B4B)
            )
            Spacer(modifier = Modifier.height(6.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                val actions = listOf(
                    Triple(Strings.tr("action_send", language), Icons.AutoMirrored.Filled.Send, ScreenRoute.TRANSFERS),
                    Triple(Strings.tr("action_recharge", language), Icons.Default.PhoneAndroid, ScreenRoute.TRANSFERS),
                    Triple(Strings.tr("action_bills", language), Icons.Default.ElectricBolt, ScreenRoute.TRANSFERS),
                    Triple(Strings.tr("action_cards", language), Icons.Default.CreditCard, ScreenRoute.CARDS)
                )

                actions.forEach { (label, icon, route) ->
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .then(GlassEffects.crystalEdgeModifier(RoundedCornerShape(14.dp), 3.dp))
                            .clickable { viewModel.navigateTo(route) }
                            .padding(vertical = 12.dp, horizontal = 4.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(38.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(Color(0xFFEEF2FF)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = label,
                                    tint = Color(0xFF4F46E5),
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Text(
                                text = label,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF1E1B4B)
                            )
                        }
                    }
                }
            }
        }

        // Prismatic Transaction Records
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
                    color = Color(0xFF1E1B4B)
                )
                Text(
                    text = Strings.tr("see_all", language),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF6366F1),
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
private fun CrystalEdgeBottomNav(
    currentScreen: ScreenRoute,
    language: Language,
    onNavigate: (ScreenRoute) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(horizontal = 14.dp, vertical = 8.dp),
        shape = RoundedCornerShape(22.dp),
        color = Color(0xFFFFFFFF).copy(alpha = 0.94f),
        border = androidx.compose.foundation.BorderStroke(1.2.dp, Color(0xFFC7D2FE)),
        shadowElevation = 8.dp
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
                        .clip(RoundedCornerShape(12.dp))
                        .background(if (isSelected) Color(0xFF6366F1).copy(alpha = 0.16f) else Color.Transparent)
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
                            tint = if (isSelected) Color(0xFF4F46E5) else Color(0xFF64748B),
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = title,
                            fontSize = 10.sp,
                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                            color = if (isSelected) Color(0xFF4F46E5) else Color(0xFF64748B)
                        )
                    }
                }
            }
        }
    }
}
