package com.wanggk.study.canvas

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

class MyCanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mPaint2 = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mRectF = RectF()

    private val mRadius = dip2px(context, 80f)

    init {
        mPaint.color = Color.RED
        mPaint.strokeWidth = dip2px(context, 20f)
        mPaint.style = Paint.Style.FILL_AND_STROKE

        mPaint2.color = Color.BLUE
        mPaint2.strokeWidth = dip2px(context, 20f)
        mPaint2.style = Paint.Style.STROKE

        mRectF.set(100f, 100f, 500f, 500f)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2
        val centerY = height / 2

        // 画圆
//        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), mRadius,mPaint)

        // 画矩形
//        canvas.drawRect(100f, 100f, 500f, 500f, mPaint)
//        canvas.drawRect(700F, 100F, 1100F, 500F, mPaint2)
//        canvas.drawRect(mRectF, mPaint)

        // 画点
        // 圆形点
//        mPaint.strokeCap = Paint.Cap.ROUND
        // 方形点
//        mPaint.strokeCap = Paint.Cap.BUTT
//        canvas.drawPoint(100f, 100f, mPaint)
        // 画一批点
//        val points = floatArrayOf(0f, 0f, 50f, 50f, 50f, 100f, 100f, 50f, 100f, 100f, 150f, 50f, 150f, 100f)
//        canvas.drawPoints(points, 2, 4, mPaint )


        // 画椭圆
//        canvas.drawOval(400f, 50f, 700f, 200f, mPaint2)
//        canvas.drawOval(50f, 50f, 350f, 200f, mPaint)

        // 画线
//        mPaint.strokeWidth = dip2px(context, 2f)
//        canvas.drawLine(200F, 200F, 800F, 500F, mPaint)
        // 画一批线
//        val floats = floatArrayOf(20f, 20f, 120f, 20f, 70f, 20f, 70f, 120f, 20f, 120f, 120f, 120f, 150f, 20f, 250f, 20f, 150f, 20f, 150f, 120f, 250f, 20f, 250f, 120f, 150f, 120f, 250f, 120f)
//        canvas.drawLines(floats, mPaint)

        // 画圆角矩形
//        canvas.drawRoundRect(100f, 100f, 500f, 300f, 50f, 50f, mPaint)

        // 画扇形
//        mPaint2.strokeCap = Paint.Cap.ROUND
//        canvas.drawArc(200f, 100f, 800f, 700f, -90f, 90f, false, mPaint2)

        canvas.drawColor(ContextCompat.getColor(context, R.color.purple_500))
        // 画路径
        val path = Path()
        // 使用 path 对图形进行描述（这段描述代码不必看懂）
        path.addArc(200f, 200f, 400f, 400f, -225f, 225f);
        path.arcTo(400f, 200f, 600f, 400f, -180f, 225f, false);
        path.lineTo(400f, 542f);
        canvas.drawPath(path, mPaint)
    }

    private fun dip2px(context: Context, dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }
}