package com.wanggk.study.propertyanimation1

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.util.Log
import android.view.View


class SportsView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    companion object{
        private val TAG = SportsView::class.java.simpleName
    }
    private val mArcRectF = RectF()

    private var mProgress: Float = 0f

    private val radius: Float = dip2px(context,80f)

    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.textSize = dip2px(context, 40f)
        mPaint.textAlign = Paint.Align.CENTER
    }

    fun setProcess(process: Float) {
        Log.d(TAG, "setProcess : $process")
        mProgress = process
        invalidate()
    }

    fun getProcess(): Float {
        return mProgress
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = (width / 2).toFloat()
        val centerY = (height / 2).toFloat()

        mPaint.color = Color.parseColor("#E91E63")
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeCap = Paint.Cap.ROUND
        mPaint.strokeWidth = dip2px(context,15f)
        mArcRectF.set(centerX - radius, centerY - radius, centerX + radius, centerY + radius)
        canvas.drawArc(mArcRectF, -90f, mProgress * 2.7f, false, mPaint)

        mPaint.color = Color.RED
        mPaint.style = Paint.Style.FILL
        canvas.drawText( "$mProgress%", centerX, centerY - (mPaint.ascent() + mPaint.descent()) / 2, mPaint)
    }

    private fun dip2px(context: Context, dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }
}