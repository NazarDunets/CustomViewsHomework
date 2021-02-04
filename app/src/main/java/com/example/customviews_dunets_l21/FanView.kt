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

class FanView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : View(context, attrs, defStyleAttr) {

    companion object {
        private const val BG_COLOR = Color.CYAN
        private const val FAN_COLOR = Color.WHITE
    }

    private val paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var centerX = 0f
    private var centerY = 0f
    private var radius = 0f
    private var rotationDegrees = 0f
    private var rotationSpeed = 0

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        centerX = w / 2f
        centerY = h / 2f
        radius = centerX.coerceAtMost(centerY)
    }

    override fun onDraw(canvas: Canvas) {

        canvas.translate(centerX, centerY)
        canvas.rotate(rotation(rotationSpeed))
        canvas.translate(-centerX, -centerY)
        postInvalidateOnAnimation()

        super.onDraw(canvas)

        drawBg(canvas)
        drawFanCenter(canvas)
        drawFanWings(canvas)
    }

    fun setSpeed(speed: Int) {
        rotationSpeed = speed
    }

    private fun rotation(delta: Int): Float {
        rotationDegrees = (rotationDegrees + delta) % 360
        return rotationDegrees
    }

    private fun drawBg(canvas: Canvas) {
        paint.apply {
            color = BG_COLOR
            style = Paint.Style.FILL
        }

        canvas.drawCircle(centerX, centerY, radius, paint)
    }

    private fun drawFanCenter(canvas: Canvas) {
        paint.apply {
            color = FAN_COLOR
            style = Paint.Style.FILL
        }

        canvas.drawCircle(centerX, centerY, radius * 0.2f, paint)
    }

    private fun drawFanWings(canvas: Canvas) {
        paint.apply {
            color = FAN_COLOR
            style = Paint.Style.STROKE
            strokeWidth = radius * 0.2f
            strokeCap = Paint.Cap.ROUND
        }

        val endPoint = PointF().apply {
            getOffsetCoordinates(PI * 0.66, 0.7, radius, centerX, centerY)
        }
        canvas.drawLine(centerX, centerY, endPoint.x, endPoint.y, paint)

        endPoint.getOffsetCoordinates(PI * 1.32, 0.7, radius, centerX, centerY)
        canvas.drawLine(centerX, centerY, endPoint.x, endPoint.y, paint)

        endPoint.getOffsetCoordinates(PI * 1.98, 0.7, radius, centerX, centerY)
        canvas.drawLine(centerX, centerY, endPoint.x, endPoint.y, paint)

    }

}