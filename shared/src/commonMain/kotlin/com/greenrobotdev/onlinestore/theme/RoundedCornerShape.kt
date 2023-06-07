package com.greenrobotdev.onlinestore.theme

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.dp

class CircleShape(
    val circle : CornerBasedShape = RoundedCornerShape(100.dp)
)

class RoundedCornerShape(
    val custom: CornerBasedShape = RoundedCornerShape(
        topStartPercent = 60,
        topEndPercent = 0,
        bottomEndPercent = 0,
        bottomStartPercent = 60
    )
)

internal val LocalRoundedCornerShapes = staticCompositionLocalOf { com.greenrobotdev.onlinestore.theme.RoundedCornerShape() }