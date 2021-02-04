package com.example.customviews_dunets_l21.utils

import android.graphics.PointF
import kotlin.math.cos
import kotlin.math.sin

object LocalUtils {

    fun PointF.getOffsetCoordinates(
        angle: Double,
        offsetPercent: Double,
        radius: Float,
        centerX: Float,
        centerY: Float
    ) {
        x = (radius * offsetPercent * cos(angle) + centerX).toFloat()
        y = (radius * offsetPercent * sin(angle) + centerY).toFloat()
    }
}