package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object GlassEffects {

    // ONE Bank Brand Colors: Authentic Red for Logos & Primary Brand Accents
    val OneBankRed = Color(0xFFE82E2E)
    val OneBankRedDark = Color(0xFFC61C1C)
    val OneBankRedSoft = Color(0xFFF5F3FA) // Refined soft pearl/lavender neutral for badges
    val OneBankRedBorder = Color(0xFFEAE7F2) // Delicate neutral glass border

    /**
     * Subtle, premium Liquid Glass full-screen background:
     * Pure white blended smoothly with very light lavender, pale lilac, and a hint of cool pearl grey.
     * ZERO red or pink background tint.
     * Soft, oversized radial gradients blend seamlessly into white to create a gentle liquid-like atmosphere.
     */
    fun Modifier.ambientLiquidBackground(): Modifier = this.drawBehind {
        val width = size.width
        val height = size.height

        // 1. Base luminous canvas: pure white transitioning into soft pearl-lavender light
        drawRect(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color(0xFFFFFFFF),
                    Color(0xFFFBFBFE),
                    Color(0xFFF7F5FC),
                    Color(0xFFF9F8FD),
                    Color(0xFFFFFFFF)
                ),
                startY = 0f,
                endY = height
            )
        )

        // 2. Top-left very light lavender aura (oversized, seamless blend into white)
        drawCircle(
            brush = Brush.radialGradient(
                0.0f to Color(0xFFDFD6F7).copy(alpha = 0.65f),
                0.35f to Color(0xFFEBE4FB).copy(alpha = 0.40f),
                0.70f to Color(0xFFF6F3FE).copy(alpha = 0.18f),
                1.0f to Color.Transparent,
                center = Offset(width * 0.12f, height * 0.08f),
                radius = width * 1.15f
            )
        )

        // 3. Top-right pale lilac & cool pearl grey glow
        drawCircle(
            brush = Brush.radialGradient(
                0.0f to Color(0xFFDEE5F6).copy(alpha = 0.60f),
                0.40f to Color(0xFFECEFF9).copy(alpha = 0.35f),
                0.75f to Color(0xFFF7F9FD).copy(alpha = 0.15f),
                1.0f to Color.Transparent,
                center = Offset(width * 0.92f, height * 0.16f),
                radius = width * 1.05f
            )
        )

        // 4. Mid-canvas subtle pale lilac liquid diffusion
        drawCircle(
            brush = Brush.radialGradient(
                0.0f to Color(0xFFE5DCFA).copy(alpha = 0.55f),
                0.38f to Color(0xFFEFE8FC).copy(alpha = 0.32f),
                0.75f to Color(0xFFFAF7FF).copy(alpha = 0.12f),
                1.0f to Color.Transparent,
                center = Offset(width * 0.40f, height * 0.40f),
                radius = width * 1.30f
            )
        )

        // 5. Mid-right cool pearl grey luminous mist
        drawCircle(
            brush = Brush.radialGradient(
                0.0f to Color(0xFFE0E7F5).copy(alpha = 0.55f),
                0.45f to Color(0xFFEFF3FA).copy(alpha = 0.28f),
                0.80f to Color(0xFFF9FAFE).copy(alpha = 0.10f),
                1.0f to Color.Transparent,
                center = Offset(width * 0.96f, height * 0.60f),
                radius = width * 1.05f
            )
        )

        // 6. Lower-left delicate lavender & cool pearl aura
        drawCircle(
            brush = Brush.radialGradient(
                0.0f to Color(0xFFDFD7F8).copy(alpha = 0.60f),
                0.36f to Color(0xFFECE5FA).copy(alpha = 0.35f),
                0.72f to Color(0xFFF7F4FD).copy(alpha = 0.15f),
                1.0f to Color.Transparent,
                center = Offset(width * 0.10f, height * 0.82f),
                radius = width * 1.20f
            )
        )

        // 7. Lower-right gentle pale lilac & pearl anchor
        drawCircle(
            brush = Brush.radialGradient(
                0.0f to Color(0xFFE5DDF7).copy(alpha = 0.50f),
                0.45f to Color(0xFFF1EBFB).copy(alpha = 0.25f),
                1.0f to Color.Transparent,
                center = Offset(width * 0.85f, height * 0.92f),
                radius = width * 0.95f
            )
        )
    }

    // Refined Liquid Precision Surface: translucent white with restrained glass effects & delicate shadows
    fun liquidPrecisionSurface(
        shape: Shape = RoundedCornerShape(20.dp),
        elevation: Dp = 3.dp
    ): Modifier = Modifier
        .shadow(
            elevation = elevation,
            shape = shape,
            ambientColor = Color(0x0A1E1B4B),
            spotColor = Color(0x0E000000)
        )
        .clip(shape)
        .background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    Color.White.copy(alpha = 0.96f),
                    Color(0xFFFCFBFE).copy(alpha = 0.92f),
                    Color(0xFFF9F7FC).copy(alpha = 0.86f)
                )
            )
        )
        .border(
            width = 1.dp,
            brush = Brush.linearGradient(
                colors = listOf(
                    Color.White,
                    Color(0xFFECEAF4).copy(alpha = 0.75f),
                    Color(0xFFE4E1F0).copy(alpha = 0.50f)
                )
            ),
            shape = shape
        )

    // Tap-For-Balance Pill Surface: translucent white with restrained glass highlight
    fun balancePillSurface(
        shape: Shape = RoundedCornerShape(24.dp),
        elevation: Dp = 2.5.dp
    ): Modifier = Modifier
        .shadow(
            elevation = elevation,
            shape = shape,
            ambientColor = Color(0x0A1E1B4B),
            spotColor = Color(0x10000000)
        )
        .clip(shape)
        .background(Color.White.copy(alpha = 0.92f))
        .border(
            width = 1.dp,
            color = Color(0xFFEAE7F2),
            shape = shape
        )

    // Quick-action Tile Surface: translucent white with refined pearl border
    fun actionTileSurface(
        shape: Shape = RoundedCornerShape(16.dp),
        elevation: Dp = 1.5.dp
    ): Modifier = Modifier
        .shadow(
            elevation = elevation,
            shape = shape,
            ambientColor = Color(0x081E1B4B),
            spotColor = Color(0x0C000000)
        )
        .clip(shape)
        .background(Color.White.copy(alpha = 0.90f))
        .border(
            width = 1.dp,
            color = Color(0xFFECE9F4).copy(alpha = 0.80f),
            shape = shape
        )

    // Solid/translucent white card surface with subtle border and shadow for list items
    fun solidWhiteSurface(
        shape: Shape = RoundedCornerShape(16.dp),
        elevation: Dp = 1.5.dp
    ): Modifier = Modifier
        .shadow(
            elevation = elevation,
            shape = shape,
            ambientColor = Color(0x061E1B4B),
            spotColor = Color(0x0A000000)
        )
        .clip(shape)
        .background(Color.White.copy(alpha = 0.92f))
        .border(
            width = 1.dp,
            color = Color(0xFFECEAF4).copy(alpha = 0.80f),
            shape = shape
        )

    // Backwards-compatible modifiers for concept screens
    fun liquidGlassModifier(
        shape: Shape = RoundedCornerShape(24.dp),
        elevation: Dp = 6.dp
    ): Modifier = Modifier
        .shadow(elevation, shape, ambientColor = Color(0x081E1B4B), spotColor = Color(0x0E000000))
        .clip(shape)
        .background(Color.White.copy(alpha = 0.92f))
        .border(1.dp, Color(0xFFECEAF4), shape)

    fun quietLuxuryModifier(
        shape: Shape = RoundedCornerShape(16.dp),
        elevation: Dp = 4.dp
    ): Modifier = Modifier.shadow(elevation, shape).clip(shape).background(Color(0xFFFCFBF8)).border(0.8.dp, Color(0xFFD4AF37), shape)

    fun auroraFlowModifier(
        shape: Shape = RoundedCornerShape(22.dp),
        elevation: Dp = 6.dp
    ): Modifier = Modifier.shadow(elevation, shape).clip(shape).background(Color.White.copy(alpha = 0.92f)).border(1.dp, Color(0xFFE2E8F0), shape)

    fun precisionModifier(
        shape: Shape = RoundedCornerShape(14.dp),
        elevation: Dp = 3.dp
    ): Modifier = Modifier.shadow(elevation, shape).clip(shape).background(Color.White.copy(alpha = 0.92f)).border(1.dp, Color(0xFFE2E8F0), shape)

    fun organicCalmModifier(
        shape: Shape = RoundedCornerShape(28.dp),
        elevation: Dp = 5.dp
    ): Modifier = Modifier.shadow(elevation, shape).clip(shape).background(Color.White.copy(alpha = 0.92f)).border(1.dp, Color(0xFFE2E8F0), shape)

    fun crystalEdgeModifier(
        shape: Shape = RoundedCornerShape(20.dp),
        elevation: Dp = 8.dp
    ): Modifier = Modifier.shadow(elevation, shape).clip(shape).background(Color.White.copy(alpha = 0.92f)).border(1.2.dp, Color(0xFFE2E8F0), shape)

    fun bentoTileSurface(
        shape: Shape = RoundedCornerShape(12.dp)
    ): Modifier = Modifier.clip(shape).background(Color.White.copy(alpha = 0.92f)).border(1.dp, Color(0xFFECEAF4), shape)
}
