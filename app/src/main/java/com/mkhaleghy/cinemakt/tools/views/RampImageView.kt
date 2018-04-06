package com.mkhaleghy.cinemakt.tools.views

import android.content.Context
import android.graphics.*
import android.support.v7.widget.AppCompatImageView
import android.util.AttributeSet
import com.mkhaleghy.cinemakt.R

/**
 * Created by mk on 3/8/2018.
 */

class RampImageView(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatImageView(context, attrs, defStyleAttr) {
    val TAG = "RampImageView"
    private var rampHeight = 0
    private var paint = Paint()
    private var rampColor = 0
    private var rampBaias = 0F
    private var rampDy = 0F
        set(value) {
            field = value
            invalidate()
        }

    private fun init(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.RampImageView)

        rampHeight = ta.getDimensionPixelSize(R.styleable.RampImageView_riv_rampHeight, 0)
        rampBaias = ta.getFloat(R.styleable.RampImageView_riv_rampStartPercent, 0f)
        rampColor = ta.getColor(R.styleable.RampImageView_riv_rampColor, Color.WHITE)

        paint.color = rampColor
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val rampStartY = rampStartY()
        drawBaseDownTriangle(0, rampStartY(), width, rampHeight, paint, canvas)
        canvas.drawRect(0f, rampStartY.toFloat(), width.toFloat(), height.toFloat(), paint)
    }

    private fun rampStartY(): Int {
        return (height - height * rampBaias + rampDy).toInt()
    }

    private fun drawBaseDownTriangle(x: Int, y: Int, width: Int, height: Int, paint: Paint, canvas: Canvas) {

        val p1 = Point(x, y)
        val pointX = x + width
        val pointY = y - height

        val p2 = Point(pointX, pointY)
        val p3 = Point(x + width, y)


        val path = Path()
        path.fillType = Path.FillType.EVEN_ODD
        path.moveTo(p1.x.toFloat(), p1.y.toFloat())
        path.lineTo(p2.x.toFloat(), p2.y.toFloat())
        path.lineTo(p3.x.toFloat(), p3.y.toFloat())
        path.close()

        canvas.drawPath(path, paint)
    }

    init {
        init(attrs)
    }
}
