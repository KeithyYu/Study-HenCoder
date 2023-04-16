package com.wanggk.study.canvasmatrix

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

/**
 * 1 范围裁切
 * 范围裁切有两个方法： clipRect() 和 clipPath()。裁切方法之后的绘制代码，都会被限制在裁切范围内。
 */
class ClipView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val mRadius = dip2px(context, 40f)

    init {
        mPaint.color = ContextCompat.getColor(context, R.color.purple_500)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX = width / 2
        val centerY = height / 2
        // 1.1 clipRect()
        // 记得要加上 Canvas.save() 和 Canvas.restore() 来及时恢复绘制范围
        canvas.save()
        canvas.clipRect(centerX - dip2px(context, 20f), centerY - dip2px(context, 20f), centerX + dip2px(context, 20f), centerY + dip2px(context, 20f))
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), mRadius, mPaint)
        canvas.restore()

        // 1.2 clipPath()
        // 1.2.1 圆心坐标
        val centerX2 = centerX + mRadius * 2
        val centerY2 = centerY + mRadius * 2

        // 1.2.2 辐射性颜色
        val radialGradient = RadialGradient(centerX2, centerY2, mRadius, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"), Shader.TileMode.CLAMP)
        mPaint.shader = radialGradient

        // 1.2.3 绘制一个三角形路径
        val path = Path()
        path.moveTo(centerX2, centerY2 - mRadius) // 此点为多边形的起点
        path.lineTo(centerX2 - mRadius, centerY2)
        path.lineTo(centerX2 + mRadius, centerY2)
        path.close() // 使这些点构成封闭的多边形

        // 1.2.4 先保存，再绘制
        canvas.save()
        canvas.clipPath(path)
        canvas.drawCircle(centerX2, centerY2, mRadius, mPaint)
        canvas.restore()
    }

    private fun dip2px(context: Context, dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }
}