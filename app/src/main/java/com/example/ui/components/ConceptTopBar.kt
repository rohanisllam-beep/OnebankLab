package com.example.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.GridView
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.localization.Strings
import com.example.model.ConceptId
import com.example.model.Language

@Composable
fun ConceptTopBar(
    currentConcept: ConceptId,
    language: Language,
    isBalanceHidden: Boolean,
    onBackToShowroom: () -> Unit,
    onSelectConcept: (ConceptId) -> Unit,
    onToggleLanguage: () -> Unit,
    onTogglePrivacy: () -> Unit,
    modifier: Modifier = Modifier
) {
    var menuExpanded by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 6.dp),
        shape = RoundedCornerShape(20.dp),
        color = Color.White.copy(alpha = 0.94f),
        shadowElevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Showroom button
            Row(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .background(Color(0xFF0F2552).copy(alpha = 0.08f))
                    .clickable { onBackToShowroom() }
                    .padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Showroom",
                    tint = Color(0xFF0F2552),
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = Strings.tr("back_to_showroom", language),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F2552)
                )
            }

            // Concept Switcher Dropdown Pill
            Box {
                Row(
                    modifier = Modifier
                        .clip(RoundedCornerShape(14.dp))
                        .background(currentConcept.primaryColor.copy(alpha = 0.10f))
                        .clickable { menuExpanded = true }
                        .padding(horizontal = 10.dp, vertical = 6.dp)
                        .animateContentSize(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(currentConcept.accentColor)
                    )
                    Text(
                        text = "Concept ${currentConcept.number}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = currentConcept.primaryColor
                    )
                    Icon(
                        imageVector = Icons.Default.GridView,
                        contentDescription = "Switch",
                        tint = currentConcept.primaryColor,
                        modifier = Modifier.size(14.dp)
                    )
                }

                DropdownMenu(
                    expanded = menuExpanded,
                    onDismissRequest = { menuExpanded = false }
                ) {
                    ConceptId.entries.forEach { concept ->
                        DropdownMenuItem(
                            text = {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(10.dp)
                                            .clip(CircleShape)
                                            .background(concept.primaryColor)
                                    )
                                    Text(
                                        text = "${concept.number}. ${if (language == Language.BN) concept.titleBn else concept.title}",
                                        fontSize = 13.sp,
                                        fontWeight = if (concept == currentConcept) FontWeight.Bold else FontWeight.Normal,
                                        color = if (concept == currentConcept) concept.primaryColor else Color.Black
                                    )
                                }
                            },
                            onClick = {
                                menuExpanded = false
                                onSelectConcept(concept)
                            }
                        )
                    }
                }
            }

            // Right Actions: Language Switch & Balance Privacy
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                // Language Switch
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFE84538).copy(alpha = 0.12f))
                        .clickable { onToggleLanguage() }
                        .padding(horizontal = 9.dp, vertical = 5.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (language == Language.EN) "বাং" else "EN",
                        fontSize = 11.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = Color(0xFFE84538)
                    )
                }

                // Balance Visibility Toggle
                IconButton(
                    onClick = onTogglePrivacy,
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (isBalanceHidden) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                        contentDescription = "Toggle Balance",
                        tint = Color(0xFF475569),
                        modifier = Modifier.size(18.dp)
                    )
                }
            }
        }
    }
}
