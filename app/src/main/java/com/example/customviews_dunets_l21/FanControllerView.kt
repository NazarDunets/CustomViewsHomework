package com.example.customviews_dunets_l21

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import com.example.customviews_dunets_l21.utils.LocalUtils.getOffsetCoordinates
import kotlin.math.PI

class FanControllerView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val BG_COLOR = Color.GREEN
        private const val MARKER_COLOR = Color.BLACK
        private const val STARTING_MARKER_ANGLE = PI * -0.8
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f

    private val labels = listOf(0, 1, 2, 3)

    private var _power = 0
    val power get() = _power

    init {
        isClickable = true
    }

    override fun performClick(): Boolean {
        changePower()
        invalidate()
        super.performClick()
        return true

    }

    private fun changePower() {
        _power = (_power + 1) % 4
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        radius = centerX.coerceAtMost(centerY) * 0.8f
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        drawBg(canvas)
        drawMarker(canvas)
        drawLabels(canvas)
    }

    private fun drawBg(canvas: Canvas) {
        paint.apply {
            color = BG_COLOR
            style = Paint.Style.FILL
        }

        canvas.drawCircle(centerX, centerY, radius, paint)
    }

    private fun drawMarker(canvas: Canvas) {
        paint.apply {
            color = MARKER_COLOR
            style = Paint.Style.STROKE
            strokeCap = Paint.Cap.ROUND
            strokeWidth = radius * 0.2f
        }

        val markerCoordinates = PointF().apply {
            val angle = STARTING_MARKER_ANGLE + PI * 0.2 * power
            getOffsetCoordinates(angle, 0.8, radius, centerX, centerY)
        }

        canvas.drawPoint(markerCoordinates.x, markerCoordinates.y, paint)
    }

    private fun drawLabels(canvas: Canvas) {
        paint.apply {
            style = Paint.Style.FILL_AND_STROKE
            color = MARKER_COLOR
            strokeWidth = 2f
            textSize = radius * 0.2f
            textAlign = Paint.Align.CENTER
        }
        for (label in labels) {
            val angle = STARTING_MARKER_ANGLE + PI * 0.2 * label
            val labelCoordinates = PointF().apply {
                getOffsetCoordinates(angle, 1.1, radius, centerX, centerY)
            }
            canvas.drawText(label.toString(), labelCoordinates.x, labelCoordinates.y, paint)
        }
    }


}