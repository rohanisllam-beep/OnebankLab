package com.example.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OneBankBrandLogo(
    modifier: Modifier = Modifier,
    iconSize: Dp = 38.dp,
    showText: Boolean = true,
    showSlogan: Boolean = false,
    sloganText: String = "Always there for you",
    textColor: Color = Color(0xFF1E1B1B),
    accentColor: Color = Color(0xFFE82E2E)
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        // Authentic ONE Bank Circular Emblem with Stylized "1"
        Box(
            modifier = Modifier
                .size(iconSize)
                .clip(CircleShape)
                .background(accentColor),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(iconSize * 0.72f)) {
                val strokeWidth = size.width * 0.10f
                // Outer swirl ring
                drawCircle(
                    color = Color.White.copy(alpha = 0.45f),
                    radius = size.width * 0.44f,
                    style = Stroke(width = strokeWidth)
                )
                // Concentric inner swirl arc
                drawArc(
                    color = Color.White,
                    startAngle = -35f,
                    sweepAngle = 210f,
                    useCenter = false,
                    style = Stroke(width = strokeWidth * 1.25f)
                )
                // Stylized "1"
                val path = Path().apply {
                    val w = size.width
                    val h = size.height
                    moveTo(w * 0.36f, h * 0.38f)
                    lineTo(w * 0.50f, h * 0.25f)
                    lineTo(w * 0.50f, h * 0.78f)
                    moveTo(w * 0.34f, h * 0.78f)
                    lineTo(w * 0.66f, h * 0.78f)
                }
                drawPath(
                    path = path,
                    color = Color.White,
                    style = Stroke(width = strokeWidth * 1.45f)
                )
            }
        }

        if (showText) {
            Column(verticalArrangement = Arrangement.Center) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Text(
                        text = "ONE",
                        fontSize = (iconSize.value * 0.44f).sp,
                        fontWeight = FontWeight.Black,
                        color = accentColor,
                        letterSpacing = 0.5.sp
                    )
                    Text(
                        text = "BANK",
                        fontSize = (iconSize.value * 0.44f).sp,
                        fontWeight = FontWeight.Bold,
                        color = textColor,
                        letterSpacing = 0.8.sp
                    )
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(3.dp))
                            .background(accentColor.copy(alpha = 0.12f))
                            .padding(horizontal = 4.dp, vertical = 1.dp)
                    ) {
                        Text(
                            text = "PLC",
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            color = accentColor
                        )
                    }
                }

                if (showSlogan) {
                    Text(
                        text = sloganText,
                        fontSize = 9.sp,
                        color = Color(0xFF757575),
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}
