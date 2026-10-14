package com.example.ui.showroom

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.Strings
import com.example.model.ConceptId
import com.example.model.Language
import com.example.state.BankAppViewModel
import com.example.state.BankUiState
import com.example.ui.components.OneBankBrandLogo

@Composable
fun ShowroomScreen(
    state: BankUiState,
    viewModel: BankAppViewModel,
    modifier: Modifier = Modifier
) {
    val language = state.language
    var isResearchExpanded by remember { mutableStateOf(false) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFF0F4F8),
                        Color(0xFFF8FAFC),
                        Color.White
                    )
                )
            )
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Showroom Top Bar
        item {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                OneBankBrandLogo(iconSize = 36.dp, showText = true)

                // Language Switch pill
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .clickable { viewModel.toggleLanguage() },
                    shape = RoundedCornerShape(12.dp),
                    color = Color.White,
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color(0xFFE2E8F0)),
                    shadowElevation = 1.dp
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Language,
                            contentDescription = "Language",
                            tint = Color(0xFF0F2552),
                            modifier = Modifier.size(14.dp)
                        )
                        Text(
                            text = if (language == Language.EN) "বাংলা" else "English",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F2552)
                        )
                    }
                }
            }
        }

        // Hero Title & Description
        item {
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(Color(0xFF0F2552).copy(alpha = 0.08f))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = Strings.tr("showroom_badge", language),
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF0F2552),
                        letterSpacing = 0.5.sp
                    )
                }

                Text(
                    text = Strings.tr("showroom_title", language),
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF0A1C3E),
                    letterSpacing = (-0.5).sp
                )

                Text(
                    text = Strings.tr("showroom_subtitle", language),
                    fontSize = 13.sp,
                    color = Color(0xFF475569),
                    lineHeight = 18.sp
                )
            }
        }

        // Global Fintech Research & Design Strategy Collapsible Panel
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
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { isResearchExpanded = !isResearchExpanded },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = "Research",
                                tint = Color(0xFFE84538),
                                modifier = Modifier.size(20.dp)
                            )
                            Text(
                                text = if (language == Language.BN) "গ্লোবাল ব্যাংকিং গবেষণা ও কৌশল" else "Global Banking Research & Strategy",
                                fontSize = 13.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(0xFF0F2552)
                            )
                        }
                        Icon(
                            imageVector = if (isResearchExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
                            contentDescription = "Toggle",
                            tint = Color(0xFF64748B)
                        )
                    }

                    AnimatedVisibility(visible = isResearchExpanded) {
                        Column(
                            modifier = Modifier.padding(top = 12.dp),
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            HorizontalDivider(color = Color(0xFFF1F5F9))
                            ResearchBulletPoint(
                                title = "Revolut & Monzo Benchmark",
                                desc = if (language == Language.BN) "স্বচ্ছ ব্যালান্স তথ্য, ইনস্ট্যান্ট ট্রান্সফার এবং এক-ক্লিক গোপনীয়তা নিয়ন্ত্রণ।" else "Clean balance hierarchy, instant money transfers, and tap-to-hide privacy."
                            )
                            ResearchBulletPoint(
                                title = "Apple Liquid Glass Aesthetic",
                                desc = if (language == Language.BN) "নরম ফ্রস্টেড কাঁচ, সূক্ষ্ম আলো-ছায়া ও চোখে আরামদায়ক সাদা জমিন।" else "Translucent specular surfaces, controlled blur, and calm near-white canvases."
                            )
                            ResearchBulletPoint(
                                title = "Authentic ONE Bank Identity",
                                desc = if (language == Language.BN) "ওয়ান ব্যাংকের নিজস্ব লোগো, এনপিএসবি/বিকাশ রাউটিং এবং ১৬২৬৯ হেল্পলাইন অক্ষুণ্ণ রাখা হয়েছে।" else "Preserved authentic ONE Bank emblem, 16269 helpline, NPSB & bKash workflows."
                            )
                        }
                    }
                }
            }
        }

        // Section header for 6 Concepts
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = Strings.tr("explore_concepts", language),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color(0xFF0F2552)
                )
                Text(
                    text = "6 Directions",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF64748B)
                )
            }
        }

        // The 6 Concept Cards
        items(ConceptId.entries) { concept ->
            val isPreferred = state.preferredConcept == concept
            ConceptShowcaseCard(
                concept = concept,
                isPreferred = isPreferred,
                language = language,
                onPreview = { viewModel.selectConcept(concept) },
                onMarkPreferred = { viewModel.setPreferredConcept(concept) }
            )
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
private fun ResearchBulletPoint(title: String, desc: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.Top
    ) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(6.dp)
                .clip(CircleShape)
                .background(Color(0xFF0F2552))
        )
        Column {
            Text(
                text = title,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF0F2552)
            )
            Text(
                text = desc,
                fontSize = 11.sp,
                color = Color(0xFF475569),
                lineHeight = 15.sp
            )
        }
    }
}

@Composable
private fun ConceptShowcaseCard(
    concept: ConceptId,
    isPreferred: Boolean,
    language: Language,
    onPreview: () -> Unit,
    onMarkPreferred: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(if (isPreferred) 6.dp else 2.dp, RoundedCornerShape(22.dp))
            .border(
                width = if (isPreferred) 2.dp else 1.dp,
                color = if (isPreferred) concept.primaryColor else Color(0xFFE2E8F0),
                shape = RoundedCornerShape(22.dp)
            ),
        shape = RoundedCornerShape(22.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Header: Number + Title + Badge
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
                            .size(34.dp)
                            .clip(CircleShape)
                            .background(concept.primaryColor),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = concept.number,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    }

                    Column {
                        Text(
                            text = if (language == Language.BN) concept.titleBn else concept.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF0F172A)
                        )
                        Text(
                            text = if (language == Language.BN) concept.subtitleBn else concept.subtitle,
                            fontSize = 11.sp,
                            color = Color(0xFF64748B)
                        )
                    }
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(concept.accentColor.copy(alpha = 0.15f))
                        .padding(horizontal = 8.dp, vertical = 3.dp)
                ) {
                    Text(
                        text = concept.badge,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.Bold,
                        color = concept.primaryColor
                    )
                }
            }

            // Representative Mini Dashboard Mockup Preview
            MiniDashboardPreview(concept = concept, language = language)

            // Philosophy Description
            Text(
                text = if (language == Language.BN) concept.philosophyBn else concept.philosophy,
                fontSize = 12.sp,
                color = Color(0xFF475569),
                lineHeight = 17.sp
            )

            // Palette Swatches
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(6.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Palette:",
                    fontSize = 10.sp,
                    color = Color(0xFF94A3B8),
                    fontWeight = FontWeight.Medium
                )
                listOf(
                    concept.primaryColor,
                    concept.accentColor,
                    concept.surfaceColor,
                    Color(0xFF0A1C3E)
                ).forEach { color ->
                    Box(
                        modifier = Modifier
                            .size(16.dp)
                            .clip(CircleShape)
                            .background(color)
                            .border(0.5.dp, Color.LightGray, CircleShape)
                    )
                }
            }

            HorizontalDivider(color = Color(0xFFF1F5F9))

            // Action Row: Preview Concept & Mark as Preferred
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(
                    onClick = onPreview,
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = concept.primaryColor)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Preview",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Text(
                            text = Strings.tr("preview_concept", language),
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }

                OutlinedButton(
                    onClick = onMarkPreferred,
                    shape = RoundedCornerShape(12.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        width = 1.dp,
                        color = if (isPreferred) concept.primaryColor else Color(0xFFCBD5E1)
                    )
                ) {
                    Icon(
                        imageVector = if (isPreferred) Icons.Default.Star else Icons.Outlined.StarOutline,
                        contentDescription = "Preferred",
                        tint = if (isPreferred) Color(0xFFF59E0B) else Color(0xFF64748B),
                        modifier = Modifier.size(18.dp)
                    )
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(
                        text = if (isPreferred) Strings.tr("preferred_choice", language) else Strings.tr("mark_as_preferred", language),
                        fontSize = 11.sp,
                        fontWeight = if (isPreferred) FontWeight.Bold else FontWeight.Normal,
                        color = if (isPreferred) concept.primaryColor else Color(0xFF475569)
                    )
                }
            }
        }
    }
}

@Composable
private fun MiniDashboardPreview(
    concept: ConceptId,
    language: Language
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(115.dp),
        shape = RoundedCornerShape(14.dp),
        color = concept.surfaceColor,
        border = androidx.compose.foundation.BorderStroke(1.dp, concept.accentColor.copy(alpha = 0.35f))
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(12.dp)
        ) {
            when (concept) {
                ConceptId.LIQUID_GLASS -> {
                    // Apple Liquid Glass Mockup with specular highlight
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("ONE Sanchay", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0F3B82))
                            Text("•••• 9201", fontSize = 10.sp, color = Color(0xFF64748B))
                        }
                        Text(
                            text = Strings.formatCurrency(248750.50, language),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF0A1C3E)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("Transfer", "TopUp", "Bills", "Cards").forEach {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(20.dp)
                                        .clip(RoundedCornerShape(6.dp))
                                        .background(Color.White.copy(alpha = 0.8f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(it, fontSize = 8.sp, color = Color(0xFF0F3B82), fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
                ConceptId.QUIET_LUXURY -> {
                    // Quiet Luxury Serif Mockup with gold hairline
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {
                        Text("ONE BANK PRIVATE", fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC5A059), letterSpacing = 2.sp)
                        Text(
                            text = Strings.formatCurrency(248750.50, language),
                            fontSize = 20.sp,
                            fontFamily = FontFamily.Serif,
                            color = Color(0xFF1F2421)
                        )
                        Box(modifier = Modifier.size(width = 30.dp, height = 1.dp).background(Color(0xFFC5A059)))
                        Text("018 2049 8192 01", fontSize = 9.sp, color = Color(0xFFA8A29E))
                    }
                }
                ConceptId.AURORA_FLOW -> {
                    // Aurora Flow wave mockup
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("Aurora Flow", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF0D9488))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(4.dp))
                                    .background(Color(0xFFA7F3D0))
                                    .padding(horizontal = 4.dp, vertical = 1.dp)
                            ) {
                                Text("LIVE", fontSize = 7.sp, fontWeight = FontWeight.Bold, color = Color(0xFF064E3B))
                            }
                        }
                        Text(
                            text = Strings.formatCurrency(248750.50, language),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF064E3B)
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                        ) {
                            listOf(Color(0xFF0D9488), Color(0xFF10B981), Color(0xFF06B6D4)).forEach { c ->
                                Box(modifier = Modifier.size(16.dp).clip(CircleShape).background(c))
                            }
                        }
                    }
                }
                ConceptId.PRECISION_FINANCIAL -> {
                    // Precision Financial Bento & Monospace mockup
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("SYS: OBL-DESK", fontSize = 9.sp, fontFamily = FontFamily.Monospace, color = Color(0xFF0047AB), fontWeight = FontWeight.Bold)
                            Text("OK", fontSize = 8.sp, fontFamily = FontFamily.Monospace, color = Color(0xFF16A34A))
                        }
                        Text(
                            text = Strings.formatCurrency(248750.50, language),
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Black,
                            fontFamily = FontFamily.Monospace,
                            color = Color(0xFF0F172A)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            listOf("SEND", "TOPUP", "BILL", "CARD").forEach {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(18.dp)
                                        .clip(RoundedCornerShape(4.dp))
                                        .background(Color.White)
                                        .border(0.8.dp, Color(0xFFCBD5E1), RoundedCornerShape(4.dp)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(it, fontSize = 7.sp, fontFamily = FontFamily.Monospace, fontWeight = FontWeight.Bold, color = Color(0xFF334155))
                                }
                            }
                        }
                    }
                }
                ConceptId.ORGANIC_CALM -> {
                    // Organic Calm Pebble mockup
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("ONE Sanchay", fontSize = 11.sp, fontWeight = FontWeight.SemiBold, color = Color(0xFF52796F))
                        Text(
                            text = Strings.formatCurrency(248750.50, language),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = Color(0xFF2D4A3E)
                        )
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            listOf("Send", "Recharge", "Bills").forEach {
                                Box(
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(20.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color(0xFFE8EDE5)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(it, fontSize = 8.sp, fontWeight = FontWeight.Medium, color = Color(0xFF2D4A3E))
                                }
                            }
                        }
                    }
                }
                ConceptId.CRYSTAL_EDGE -> {
                    // Crystal Edge Faceted mockup
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text("PRISM GEOMETRY", fontSize = 9.sp, fontWeight = FontWeight.Black, color = Color(0xFF6366F1))
                            Text("4214Linked", fontSize = 9.sp, color = Color(0xFF64748B))
                        }
                        Text(
                            text = Strings.formatCurrency(248750.50, language),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Black,
                            color = Color(0xFF1E1B4B)
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Brush.horizontalGradient(listOf(Color(0xFF6366F1), Color(0xFFEC4899))))
                        )
                    }
                }
            }
        }
    }
}
