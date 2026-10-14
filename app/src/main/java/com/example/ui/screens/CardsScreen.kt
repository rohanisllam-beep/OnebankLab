package com.example.ui.screens

import androidx.compose.animation.core.animateFloatAsState
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Contactless
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.Strings
import com.example.model.BankCard
import com.example.model.Language
import com.example.state.BankAppViewModel
import com.example.state.BankUiState

@Composable
fun CardsScreen(
    state: BankUiState,
    viewModel: BankAppViewModel,
    modifier: Modifier = Modifier
) {
    val language = state.language
    val primaryCard = state.cards[0]
    val rotation by animateFloatAsState(
        targetValue = if (state.isCardFlipped) 180f else 0f,
        animationSpec = tween(durationMillis = 500),
        label = "CardFlip"
    )

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = Strings.tr("cards_title", language),
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1E1B1B)
            )
            Text(
                text = Strings.tr("cards_subtitle", language),
                fontSize = 13.sp,
                color = Color(0xFF757575)
            )
        }

        // Interactive 3D Flippable Bank Card
        item {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(210.dp)
                    .graphicsLayer {
                        rotationY = rotation
                        cameraDistance = 12f * density
                    }
                    .clickable { viewModel.toggleCardFlip() }
            ) {
                if (rotation <= 90f) {
                    // Card Front
                    CardFrontView(
                        card = primaryCard,
                        isFrozen = primaryCard.isFrozen,
                        language = language
                    )
                } else {
                    // Card Back (mirrored back so text is upright)
                    Box(modifier = Modifier.graphicsLayer { rotationY = 180f }) {
                        CardBackView(
                            card = primaryCard,
                            language = language
                        )
                    }
                }
            }

            // Flip hint
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Flip",
                    tint = Color(0xFF64748B),
                    modifier = Modifier.size(14.dp)
                )
                Text(
                    text = Strings.tr("tap_to_flip", language),
                    fontSize = 11.sp,
                    color = Color(0xFF64748B)
                )
            }
        }

        // Card Security Controls
        item {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(14.dp)
                ) {
                    // Freeze Toggle
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .clip(CircleShape)
                                    .background(if (primaryCard.isFrozen) Color(0xFFFEE2E2) else Color(0xFFDCFCE7)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = if (primaryCard.isFrozen) Icons.Default.Lock else Icons.Default.LockOpen,
                                    contentDescription = "Status",
                                    tint = if (primaryCard.isFrozen) Color(0xFFDC2626) else Color(0xFF16A34A),
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Column {
                                Text(
                                    text = if (primaryCard.isFrozen) Strings.tr("card_frozen", language) else Strings.tr("card_active", language),
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color(0xFF1E293B)
                                )
                                Text(
                                    text = if (primaryCard.isFrozen) Strings.tr("unfreeze_card", language) else Strings.tr("freeze_card", language),
                                    fontSize = 11.sp,
                                    color = Color(0xFF64748B)
                                )
                            }
                        }

                        Switch(
                            checked = !primaryCard.isFrozen,
                            onCheckedChange = { viewModel.toggleFreezeCard(primaryCard.id) },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = Color(0xFF16A34A),
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color(0xFFCBD5E1)
                            )
                        )
                    }

                    // Daily Spending Limit
                    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = Strings.tr("daily_limit", language),
                                fontSize = 12.sp,
                                color = Color(0xFF757575)
                            )
                            Text(
                                text = "${Strings.formatCurrency(28400.0, language)} / ${Strings.formatCurrency(primaryCard.dailyLimit, language)}",
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color(0xFF1E1B1B)
                            )
                        }
                        LinearProgressIndicator(
                            progress = { 0.284f },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(6.dp)
                                .clip(CircleShape),
                            color = Color(0xFFE82E2E),
                            trackColor = Color(0xFFF3ECEC)
                        )
                    }
                }
            }
        }

        // Banking Services
        item {
            Text(
                text = Strings.tr("card_services", language),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1E293B)
            )
        }

        // Cheque Book Request
        item {
            ServiceActionCard(
                icon = Icons.Default.MenuBook,
                iconColor = Color(0xFF0284C7),
                iconBg = Color(0xFFE0F2FE),
                title = Strings.tr("request_cheque", language),
                subtitle = if (language == Language.BN) "২৫ বা ৫০ পাতার নতুন চেকবইয়ের আবেদন করুন" else "Order 25/50 leaves personalized cheque book",
                onClick = { viewModel.requestChequeBook() }
            )
        }

        // Solvency Certificate Request
        item {
            ServiceActionCard(
                icon = Icons.Default.Description,
                iconColor = Color(0xFF7E22CE),
                iconBg = Color(0xFFF3E8FF),
                title = Strings.tr("request_solvency", language),
                subtitle = if (language == Language.BN) "ভিসা বা বৃত্তির জন্য ব্যাংক সলভেন্সি সার্টিফিকেট" else "Official solvency certificate for embassy/travel",
                onClick = { viewModel.requestSolvencyCertificate() }
            )
        }

        // Branch & ATM Locator
        item {
            ServiceActionCard(
                icon = Icons.Default.LocationOn,
                iconColor = Color(0xFFE82E2E),
                iconBg = Color(0xFFF5F3FA),
                title = Strings.tr("branches_title", language),
                subtitle = if (language == Language.BN) "দেশব্যাপী ১১০+ শাখা ও এটিএম নেটওয়ার্ক খুঁজুন" else "Find nationwide 110+ branches & fast tracks",
                onClick = { viewModel.setShowBranchLocator(true) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }

    // Branch Locator Dialog
    if (state.showBranchLocatorDialog) {
        BranchLocatorDialog(
            branches = state.branches,
            language = language,
            onDismiss = { viewModel.setShowBranchLocator(false) }
        )
    }

    // Feedback Snackbar / Alert
    state.feedbackMessage?.let { msg ->
        AlertDialog(
            onDismissRequest = { viewModel.dismissFeedbackMessage() },
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Success",
                        tint = Color(0xFF16A34A)
                    )
                    Text(
                        text = if (language == Language.BN) "আবেদন সম্পন্ন" else "Request Submitted",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            },
            text = { Text(text = msg, fontSize = 13.sp, color = Color(0xFF334155)) },
            confirmButton = {
                Button(
                    onClick = { viewModel.dismissFeedbackMessage() },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE82E2E))
                ) {
                    Text(text = Strings.tr("done", language))
                }
            }
        )
    }
}

@Composable
private fun CardFrontView(
    card: BankCard,
    isFrozen: Boolean,
    language: Language
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(22.dp),
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = if (isFrozen) listOf(
                            Color(0xFF475569),
                            Color(0xFF334155),
                            Color(0xFF1E293B)
                        ) else listOf(
                            Color(0xFF0A1C3E),
                            Color(0xFF0F3B82),
                            Color(0xFF1E293B)
                        )
                    )
                )
                .padding(20.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Top Row: Logo & Contactless
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "ONE",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFFE84538)
                        )
                        Text(
                            text = "BANK",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Contactless,
                            contentDescription = "Contactless",
                            tint = Color.White.copy(alpha = 0.85f),
                            modifier = Modifier.size(22.dp)
                        )
                        Text(
                            text = card.cardType,
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF38BDF8)
                        )
                    }
                }

                // Middle: Card Number Masked
                Text(
                    text = card.cardNumberMasked,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    letterSpacing = 2.sp
                )

                // Bottom Row: Holder & Expiry
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = if (language == Language.BN) "কার্ডধারীর নাম" else "CARDHOLDER",
                            fontSize = 9.sp,
                            color = Color.White.copy(alpha = 0.6f)
                        )
                        Text(
                            text = card.cardHolder,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }

                    Column(horizontalAlignment = Alignment.End) {
                        Text(
                            text = if (language == Language.BN) "মেয়াদ" else "EXPIRES",
                            fontSize = 9.sp,
                            color = Color.White.copy(alpha = 0.6f)
                        )
                        Text(
                            text = card.expiryDate,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun CardBackView(
    card: BankCard,
    language: Language
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        shape = RoundedCornerShape(22.dp),
        shadowElevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF0F172A),
                            Color(0xFF1E293B)
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Magnetic Stripe
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(38.dp)
                        .background(Color.Black)
                )

                // Signature & CVV
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(34.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color.White),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        Text(
                            text = "  Shahriar Ahmed",
                            fontSize = 12.sp,
                            color = Color.DarkGray,
                            fontWeight = FontWeight.Medium
                        )
                    }

                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                            .background(Color(0xFFE2E8F0))
                            .padding(horizontal = 10.dp, vertical = 7.dp)
                    ) {
                        Text(
                            text = "CVV ${card.cvv}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black
                        )
                    }
                }

                // Bottom Disclaimer
                Text(
                    text = "ONE Bank PLC. 24/7 Call Center: 16269. For authorized use only.",
                    fontSize = 9.sp,
                    color = Color.White.copy(alpha = 0.5f),
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    }
}

@Composable
private fun ServiceActionCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    iconBg: Color,
    title: String,
    subtitle: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
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
            Box(
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(iconBg),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(20.dp)
                )
            }

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )
                Text(
                    text = subtitle,
                    fontSize = 11.sp,
                    color = Color(0xFF64748B)
                )
            }
        }
    }
}

@Composable
private fun BranchLocatorDialog(
    branches: List<com.example.model.BranchLocation>,
    language: Language,
    onDismiss: () -> Unit
) {
    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Branches",
                    tint = Color(0xFFE82E2E)
                )
                Text(
                    text = Strings.tr("branches_title", language),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color(0xFF1E1B1B)
                )
            }
        },
        text = {
            LazyColumn(
                modifier = Modifier.height(300.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(branches) { branch ->
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFFAFAFA),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFF3ECEC))
                    ) {
                        Column(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = if (language == Language.BN) branch.nameBn else branch.nameEn,
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF1E1B1B)
                            )
                            Text(
                                text = if (language == Language.BN) branch.addressBn else branch.addressEn,
                                fontSize = 11.sp,
                                color = Color(0xFF757575)
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Phone,
                                        contentDescription = "Phone",
                                        tint = Color(0xFF16A34A),
                                        modifier = Modifier.size(12.dp)
                                    )
                                    Text(
                                        text = branch.phone,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color(0xFF16A34A)
                                    )
                                }
                                if (branch.hasAtm) {
                                    Box(
                                        modifier = Modifier
                                            .clip(RoundedCornerShape(4.dp))
                                            .background(Color(0xFFE0F2FE))
                                            .padding(horizontal = 6.dp, vertical = 2.dp)
                                    ) {
                                        Text(
                                            text = "ATM FastTrack",
                                            fontSize = 9.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF0369A1)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = Strings.tr("close", language), fontWeight = FontWeight.Bold)
            }
        }
    )
}
