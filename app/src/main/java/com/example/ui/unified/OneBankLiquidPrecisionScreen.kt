package com.example.ui.unified

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.HeadsetMic
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material.icons.filled.QrCodeScanner
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.ReceiptLong
import androidx.compose.material.icons.filled.Savings
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.Strings
import com.example.model.Language
import com.example.model.ScreenRoute
import com.example.model.Transaction
import com.example.state.BankAppViewModel
import com.example.state.BankUiState
import com.example.ui.components.GlassEffects.OneBankRed
import com.example.ui.components.GlassEffects.OneBankRedBorder
import com.example.ui.components.GlassEffects.OneBankRedDark
import com.example.ui.components.GlassEffects.OneBankRedSoft
import com.example.ui.components.GlassEffects.ambientLiquidBackground
import com.example.ui.components.OneBankBrandLogo
import com.example.ui.screens.AccountsScreen
import com.example.ui.screens.CardsScreen
import com.example.ui.screens.ProfileScreen
import com.example.ui.screens.TransfersScreen
import com.example.ui.screens.getCategoryStyling

@Composable
fun OneBankLiquidPrecisionScreen(
    state: BankUiState,
    viewModel: BankAppViewModel,
    modifier: Modifier = Modifier
) {
    val language = state.language

    // The root Scaffold is drawn directly with ambientLiquidBackground()
    // producing a visibly luminous, soft blush-red, pale rose, and warm pearl gradient canvas.
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .ambientLiquidBackground(),
        containerColor = Color.Transparent,
        topBar = {
            OneBankTopBarHeader(
                state = state,
                onToggleLanguage = { viewModel.toggleLanguage() },
                onNotificationClick = { viewModel.navigateTo(ScreenRoute.PROFILE) },
                onToggleBalance = { viewModel.toggleBalancePrivacy() }
            )
        },
        bottomBar = {
            OneBankBottomNav(
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
        ) {
            when (state.currentScreen) {
                ScreenRoute.HOME -> OneBankRealDashboard(state, viewModel)
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

/**
 * Top bar header with:
 * - User Avatar with subtle ring
 * - Greeting & User Name
 * - Authentic ONE Bank Brand Logo with official emblem
 * - Bilingual language switcher pill (বাং / EN)
 * - Notification bell with unread badge
 * - Restrained Liquid Glass "Tap for Balance" pill capsule
 */
@Composable
private fun OneBankTopBarHeader(
    state: BankUiState,
    onToggleLanguage: () -> Unit,
    onNotificationClick: () -> Unit,
    onToggleBalance: () -> Unit
) {
    val language = state.language
    val account = state.selectedAccount
    val isHome = state.currentScreen == ScreenRoute.HOME

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 4.dp),
        shape = RoundedCornerShape(22.dp),
        color = Color.White.copy(alpha = 0.90f),
        shadowElevation = 2.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFECEAF4).copy(alpha = 0.85f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Top Row: User Avatar & Greeting (Left) + ONE Bank Logo & Actions (Right)
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                // Left: User Profile Avatar & Greeting
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF5F3FA))
                            .border(1.dp, Color(0xFFE4DFEC), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "SA",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = OneBankRed
                        )
                    }

                    Column {
                        Text(
                            text = Strings.tr("greeting_evening", language),
                            fontSize = 11.sp,
                            color = Color(0xFF757575),
                            fontWeight = FontWeight.Medium
                        )
                        Text(
                            text = Strings.tr("user_name", language),
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1E1B1B)
                        )
                    }
                }

                // Right: Authentic ONE Bank Logo + Language Switch + Notification
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    // ONE Bank Brand Logo with official emblem
                    OneBankBrandLogo(
                        iconSize = 30.dp,
                        showText = false,
                        accentColor = OneBankRed
                    )

                    // Language Switch Pill (বাং / EN)
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFF5F3FA))
                            .border(1.dp, Color(0xFFEAE7F2), RoundedCornerShape(10.dp))
                            .clickable { onToggleLanguage() }
                            .padding(horizontal = 9.dp, vertical = 5.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (language == Language.BN) "বাং" else "EN",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Black,
                            color = OneBankRed
                        )
                    }

                    // Notification Bell with unread dot
                    Box(
                        modifier = Modifier
                            .size(32.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.90f))
                            .border(1.dp, Color(0xFFECEAF4), CircleShape)
                            .clickable { onNotificationClick() },
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = "Notifications",
                            tint = Color(0xFF424242),
                            modifier = Modifier.size(17.dp)
                        )
                        // Red notification indicator dot
                        Box(
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .offset(x = (-2).dp, y = 2.dp)
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(OneBankRed)
                        )
                    }
                }
            }

            // Bottom Row of Header: Liquid Glass "Tap for Balance" Capsule (Visible on Home)
            if (isHome) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { onToggleBalance() },
                    shape = RoundedCornerShape(20.dp),
                    color = Color.White.copy(alpha = 0.92f),
                    shadowElevation = 1.5.dp,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEAE7F2))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 9.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Circular Red ৳ Badge
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(OneBankRed),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "৳",
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }

                            // Animated Reveal: "Tap for Balance" vs Revealed Balance
                            Column(modifier = Modifier.animateContentSize(tween(250))) {
                                if (state.isBalanceHidden) {
                                    Text(
                                        text = Strings.tr("tap_for_balance", language),
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = Color(0xFF212121)
                                    )
                                    Text(
                                        text = "${if (language == Language.BN) account.nameBn else account.nameEn} • ${account.accountNumber.takeLast(7)}",
                                        fontSize = 10.5.sp,
                                        color = Color(0xFF757575),
                                        fontFamily = FontFamily.Monospace
                                    )
                                } else {
                                    Text(
                                        text = Strings.formatCurrency(account.balance, language),
                                        fontSize = 18.sp,
                                        fontWeight = FontWeight.Black,
                                        color = OneBankRedDark,
                                        letterSpacing = (-0.3).sp
                                    )
                                    Text(
                                        text = "${Strings.tr("available_balance", language)} • ${account.accountNumber}",
                                        fontSize = 10.5.sp,
                                        color = Color(0xFF616161)
                                    )
                                }
                            }
                        }

                        // Discreet eye indicator
                        Icon(
                            imageVector = if (state.isBalanceHidden) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                            contentDescription = "Toggle Balance",
                            tint = Color(0xFF9E9E9E),
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

/**
 * Clean ONE Bank Home Dashboard featuring authentic, verified private commercial banking features:
 * - Fund Transfer (Own OBL, Other Bank NPSB/BEFTN, bKash & Nagad MFS)
 * - Utility Bill Payment (DESCO, DPDC, WASA, Titas, Internet)
 * - Credit Card Payment (OBL VISA Platinum & Other Bank Cards)
 * - Mobile Top-up (GP, Robi, Banglalink, Airtel, Teletalk)
 * - Account Summary & Inquiries (Savings, Current, DPS, Term Deposits)
 * - DPS & Deposit Schemes (Open DPS with guaranteed profit)
 * - e-Statements (Monthly statements & audit receipts)
 * - Service Requests (Cheque book requisition, Solvency cert, Tax IT-10B)
 */
@Composable
private fun OneBankRealDashboard(
    state: BankUiState,
    viewModel: BankAppViewModel
) {
    val language = state.language
    var currentOfferIndex by remember { mutableIntStateOf(0) }

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // 1. PRIMARY VERIFIED BANKING SERVICES (4x2 Grid of Official ONE Bank Services)
        item {
            Spacer(modifier = Modifier.height(2.dp))
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                // Row 1: Fund Transfer, Bill Payment, Credit Card, Mobile Top-up
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OneBankServiceTile(
                        label = Strings.tr("action_fund_transfer", language),
                        icon = Icons.AutoMirrored.Filled.Send,
                        iconTint = OneBankRed,
                        badgeBg = Color(0xFFF5F3FA),
                        badgeBorder = Color(0xFFEAE6F3),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.TRANSFERS) }
                    )
                    OneBankServiceTile(
                        label = Strings.tr("action_bill_payment", language),
                        icon = Icons.Default.ReceiptLong,
                        iconTint = Color(0xFFD94838),
                        badgeBg = Color(0xFFF6F4FB),
                        badgeBorder = Color(0xFFEBE7F4),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.TRANSFERS) }
                    )
                    OneBankServiceTile(
                        label = Strings.tr("action_credit_card", language),
                        icon = Icons.Default.CreditCard,
                        iconTint = OneBankRed,
                        badgeBg = Color(0xFFF5F3FA),
                        badgeBorder = Color(0xFFEAE6F3),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.CARDS) }
                    )
                    OneBankServiceTile(
                        label = Strings.tr("action_mobile_topup", language),
                        icon = Icons.Default.PhoneAndroid,
                        iconTint = OneBankRed,
                        badgeBg = Color(0xFFF5F3FA),
                        badgeBorder = Color(0xFFEAE6F3),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.TRANSFERS) }
                    )
                }

                // Row 2: Account Summary, DPS & Deposits, e-Statements, Service Requests
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OneBankServiceTile(
                        label = Strings.tr("action_account_summary", language),
                        icon = Icons.Default.AccountBalance,
                        iconTint = Color(0xFF475569),
                        badgeBg = Color(0xFFF1F3F9),
                        badgeBorder = Color(0xFFE2E7F2),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.ACCOUNTS) }
                    )
                    OneBankServiceTile(
                        label = Strings.tr("action_open_dps", language),
                        icon = Icons.Default.Savings,
                        iconTint = Color(0xFF16A34A),
                        badgeBg = Color(0xFFF0FDF4),
                        badgeBorder = Color(0xFFDCFCE7),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.ACCOUNTS) }
                    )
                    OneBankServiceTile(
                        label = Strings.tr("action_statements", language),
                        icon = Icons.Default.Description,
                        iconTint = Color(0xFF4B5563),
                        badgeBg = Color(0xFFF3F4F8),
                        badgeBorder = Color(0xFFE4E6ED),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.navigateTo(ScreenRoute.ACCOUNTS) }
                    )
                    OneBankServiceTile(
                        label = Strings.tr("action_service_requests", language),
                        icon = Icons.Default.MenuBook,
                        iconTint = OneBankRed,
                        badgeBg = Color(0xFFF5F3FA),
                        badgeBorder = Color(0xFFEAE6F3),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.requestChequeBook() }
                    )
                }
            }
        }

        // 2. PROMOTIONAL OFFERS & CAMPAIGNS CAROUSEL
        item {
            val offers = listOf(
                Triple(
                    "ONE Bank VISA Platinum",
                    if (language == Language.BN) "০% সুদে সহজ ইএমআই ও ৫০০+ আউটলেটে ছাড়" else "0% EMI at 500+ Outlets & Dual Currency Access",
                    Icons.Default.CreditCard
                ),
                Triple(
                    if (language == Language.BN) "ডাবল বেনিফিট ডিপিএস স্কিম" else "Double Benefit DPS Scheme",
                    if (language == Language.BN) "৯.৫% নিশ্চিত বার্ষিক মুনাফা ও মাসিক প্রফিট সুবিধা" else "Guaranteed 9.5% p.a. return with monthly profit",
                    Icons.Default.Savings
                ),
                Triple(
                    if (language == Language.BN) "তাৎক্ষণিক ফ্রি এনপিএসবি ট্রান্সফার" else "Instant Zero-Fee NPSB Transfer",
                    if (language == Language.BN) "যেকোনো ব্যাংক ও বিকাশ/নগদে ২৪ ঘণ্টা ফ্রি ট্রান্সফার" else "24/7 instant free fund transfer to all banks & MFS",
                    Icons.AutoMirrored.Filled.Send
                )
            )

            val (title, subtitle, icon) = offers[currentOfferIndex]

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(16.dp))
                    .clickable {
                        when (currentOfferIndex) {
                            0 -> viewModel.navigateTo(ScreenRoute.CARDS)
                            1 -> viewModel.navigateTo(ScreenRoute.ACCOUNTS)
                            2 -> viewModel.navigateTo(ScreenRoute.TRANSFERS)
                        }
                    },
                shape = RoundedCornerShape(16.dp),
                color = Color.White.copy(alpha = 0.90f),
                shadowElevation = 2.dp,
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEAE7F2))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            brush = Brush.horizontalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.94f),
                                    Color(0xFFFAF9FD).copy(alpha = 0.90f),
                                    Color.White.copy(alpha = 0.94f)
                                )
                            )
                        )
                        .padding(horizontal = 14.dp, vertical = 12.dp)
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                modifier = Modifier.weight(1f),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(38.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(OneBankRed),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = icon,
                                        contentDescription = "Offer Icon",
                                        tint = Color.White,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }

                                Column {
                                    Text(
                                        text = title,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFF1E1B1B)
                                    )
                                    Text(
                                        text = subtitle,
                                        fontSize = 10.5.sp,
                                        color = Color(0xFF757575),
                                        maxLines = 1
                                    )
                                }
                            }

                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                contentDescription = "Details",
                                tint = OneBankRed,
                                modifier = Modifier.size(16.dp)
                            )
                        }

                        // Pagination Dots
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            offers.indices.forEach { index ->
                                val isSelected = index == currentOfferIndex
                                Box(
                                    modifier = Modifier
                                        .padding(horizontal = 3.dp)
                                        .size(width = if (isSelected) 16.dp else 6.dp, height = 4.dp)
                                        .clip(RoundedCornerShape(2.dp))
                                        .background(if (isSelected) OneBankRed else Color(0xFFE2E8F0))
                                        .clickable { currentOfferIndex = index }
                                )
                            }
                        }
                    }
                }
            }
        }

        // 3. SERVICE REQUISITIONS & ATM NETWORK SECTION
        item {
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = Strings.tr("section_requests", language),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1B1B)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    OneBankServiceTile(
                        label = Strings.tr("action_cheque", language),
                        icon = Icons.Default.MenuBook,
                        iconTint = OneBankRed,
                        badgeBg = Color(0xFFF5F3FA),
                        badgeBorder = Color(0xFFEAE6F3),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.requestChequeBook() }
                    )
                    OneBankServiceTile(
                        label = Strings.tr("service_solvency", language),
                        icon = Icons.Default.Description,
                        iconTint = Color(0xFF475569),
                        badgeBg = Color(0xFFF1F3F9),
                        badgeBorder = Color(0xFFE2E7F2),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.requestSolvencyCertificate() }
                    )
                    OneBankServiceTile(
                        label = if (language == Language.BN) "ট্যাক্স সনদ" else "Tax IT-10B",
                        icon = Icons.Default.Receipt,
                        iconTint = Color(0xFFD94838),
                        badgeBg = Color(0xFFF6F4FB),
                        badgeBorder = Color(0xFFEBE7F4),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.requestSolvencyCertificate() }
                    )
                    OneBankServiceTile(
                        label = Strings.tr("service_branches", language),
                        icon = Icons.Default.AccountBalance,
                        iconTint = OneBankRed,
                        badgeBg = Color(0xFFF5F3FA),
                        badgeBorder = Color(0xFFEAE6F3),
                        modifier = Modifier.weight(1f),
                        onClick = { viewModel.setShowBranchLocator(true) }
                    )
                }
            }
        }

        // 4. RECENT BANK TRANSACTIONS LEDGER (Tap to inspect realistic receipt)
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 2.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Strings.tr("recent_transactions", language),
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1E1B1B)
                )
                Text(
                    text = Strings.tr("see_all", language),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = OneBankRed,
                    modifier = Modifier.clickable { viewModel.navigateTo(ScreenRoute.ACCOUNTS) }
                )
            }
        }

        items(state.transactions.take(3)) { tx ->
            OneBankTransactionRow(
                transaction = tx,
                language = language,
                isBalanceHidden = state.isBalanceHidden,
                onClick = { viewModel.showTransactionDetail(tx) }
            )
        }

        // 5. OFFICIAL 24/7 HELPLINE ASSISTANCE CARD (16269)
        item {
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(14.dp)),
                shape = RoundedCornerShape(14.dp),
                color = Color.White.copy(alpha = 0.90f),
                border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFEAE7F2)),
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFF5F3FA)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.HeadsetMic,
                                contentDescription = "Helpline",
                                tint = OneBankRed,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Column {
                            Text(
                                text = Strings.tr("service_helpline", language),
                                fontSize = 12.5.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E1B1B)
                            )
                            Text(
                                text = if (language == Language.BN) "২৪/৭ জরুরি ব্যাংকিং সহায়তা ও কার্ড ব্লক" else "24/7 Customer support & dispute assistance",
                                fontSize = 10.5.sp,
                                color = Color(0xFF757575)
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(8.dp))
                            .background(OneBankRed)
                            .clickable { viewModel.navigateTo(ScreenRoute.PROFILE) }
                            .padding(horizontal = 10.dp, vertical = 6.dp)
                    ) {
                        Text(
                            text = if (language == Language.BN) "কল করুন" else "Call",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(18.dp))
        }
    }
}

/**
 * Premium Service Tile:
 * Uses ONE Bank red as the primary accent, blended with pale blush backgrounds,
 * white negative space, and subtle warm-grey/neutral details.
 * Avoids making every icon solid red or every tile red.
 */
@Composable
private fun OneBankServiceTile(
    label: String,
    icon: ImageVector,
    modifier: Modifier = Modifier,
    iconTint: Color = OneBankRed,
    badgeBg: Color = Color(0xFFF5F3FA),
    badgeBorder: Color = Color(0xFFEAE6F3),
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier
            .height(88.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = Color.White.copy(alpha = 0.90f),
        shadowElevation = 1.5.dp,
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFECEAF4).copy(alpha = 0.80f))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(RoundedCornerShape(11.dp))
                    .background(badgeBg)
                    .border(0.8.dp, badgeBorder, RoundedCornerShape(11.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = label,
                    tint = iconTint,
                    modifier = Modifier.size(20.dp)
                )
            }
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                text = label,
                fontSize = 10.5.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF262626),
                maxLines = 2,
                lineHeight = 12.sp,
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )
        }
    }
}

/**
 * Compact transaction row with category icon, merchant/recipient, date, and amount.
 */
@Composable
private fun OneBankTransactionRow(
    transaction: Transaction,
    language: Language,
    isBalanceHidden: Boolean,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(14.dp))
            .clickable { onClick() },
        shape = RoundedCornerShape(14.dp),
        color = Color.White.copy(alpha = 0.90f),
        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFECEAF4).copy(alpha = 0.80f)),
        shadowElevation = 1.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Icon
            val (icon, _, _) = getCategoryStyling(transaction.category, transaction.isCredit)
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
                    .background(if (transaction.isCredit) Color(0xFFE8F5E9) else Color(0xFFF5F3FA)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = transaction.category,
                    tint = if (transaction.isCredit) Color(0xFF2E7D32) else OneBankRed,
                    modifier = Modifier.size(18.dp)
                )
            }

            // Description & date
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = if (language == Language.BN) transaction.titleBn else transaction.titleEn,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color(0xFF212121)
                )
                Text(
                    text = Strings.formatDate(if (language == Language.BN) transaction.dateBn else transaction.dateEn, language),
                    fontSize = 10.sp,
                    color = Color(0xFF757575)
                )
            }

            // Amount
            val prefix = if (transaction.isCredit) "+" else "-"
            val amountText = if (isBalanceHidden) "••••" else prefix + Strings.formatCurrency(transaction.amount, language)
            Text(
                text = amountText,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold,
                color = if (transaction.isCredit) Color(0xFF2E7D32) else Color(0xFF212121),
                fontFamily = FontFamily.Monospace
            )
        }
    }
}

/**
 * Bottom navigation bar with 4 clear destinations + Raised, elevated center QR button.
 */
@Composable
private fun OneBankBottomNav(
    currentScreen: ScreenRoute,
    language: Language,
    onNavigate: (ScreenRoute) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = Color.White.copy(alpha = 0.94f),
        shadowElevation = 8.dp,
        border = androidx.compose.foundation.BorderStroke(0.5.dp, Color(0xFFEAE7F2))
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
        ) {
            // 4 Nav Destinations
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // 1. Home
                NavItem(
                    label = Strings.tr("nav_home", language),
                    icon = Icons.Default.Home,
                    isSelected = currentScreen == ScreenRoute.HOME,
                    onClick = { onNavigate(ScreenRoute.HOME) }
                )

                // 2. Accounts / History
                NavItem(
                    label = Strings.tr("nav_history", language),
                    icon = Icons.Default.ReceiptLong,
                    isSelected = currentScreen == ScreenRoute.ACCOUNTS,
                    onClick = { onNavigate(ScreenRoute.ACCOUNTS) }
                )

                // Space for Center QR Button
                Spacer(modifier = Modifier.width(64.dp))

                // 3. Cards & Limits
                NavItem(
                    label = Strings.tr("nav_cards", language),
                    icon = Icons.Default.CreditCard,
                    isSelected = currentScreen == ScreenRoute.CARDS,
                    onClick = { onNavigate(ScreenRoute.CARDS) }
                )

                // 4. More / Profile
                NavItem(
                    label = Strings.tr("nav_profile", language),
                    icon = Icons.Default.Person,
                    isSelected = currentScreen == ScreenRoute.PROFILE,
                    onClick = { onNavigate(ScreenRoute.PROFILE) }
                )
            }

            // Raised Center Floating QR Button
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .offset(y = (-14).dp)
                    .size(54.dp)
                    .shadow(6.dp, CircleShape)
                    .clip(CircleShape)
                    .background(OneBankRed)
                    .clickable { onNavigate(ScreenRoute.TRANSFERS) },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.QrCodeScanner,
                    contentDescription = "Scan QR",
                    tint = Color.White,
                    modifier = Modifier.size(26.dp)
                )
            }
        }
    }
}

@Composable
private fun NavItem(
    label: String,
    icon: ImageVector,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = if (isSelected) OneBankRed else Color(0xFF757575),
            modifier = Modifier.size(20.dp)
        )
        Text(
            text = label,
            fontSize = 10.sp,
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
            color = if (isSelected) OneBankRed else Color(0xFF757575)
        )
    }
}
