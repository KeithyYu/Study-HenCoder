package com.wanggk.study.propertyanimation1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.View
import androidx.annotation.ColorLong


class CircleView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    private var mColor: Int = Color.BLUE

    private var mRadius: Float = dip2px(context, 60f)

    private var mPosition = PointF()

    init {
        mPaint.color = Color.RED
    }

    fun setColor(@ColorLong color: Int) {
        mColor = color
        invalidate()
    }

    fun getColor(): Int {
        return mColor
    }

    fun getPosition(): PointF {
        return mPosition
    }

    fun setPosition(position: PointF) {
        mPosition = position
        invalidate()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

//        val centerX = width / 2
//        val centerY = height / 2
        mPaint.color = mColor
//        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), mRadius, mPaint)
        val innerPaddingLeft: Float = mRadius * 1
        val innterPaddingRight: Float = mRadius * 1
        val innterPaddingTop: Float = mRadius * 1
        val innterPaddingBottom: Float = mRadius * 3
        val width: Float = width - innerPaddingLeft - innterPaddingRight - mRadius * 2
        val height: Float = height - innterPaddingTop - innterPaddingBottom - mRadius * 2

        canvas.drawCircle(
            innerPaddingLeft + mRadius + width * mPosition.x,
            innterPaddingTop + mRadius + height * mPosition.y,
            mRadius,
            mPaint
        )
    }

    private fun dip2px(context: Context, dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }
}