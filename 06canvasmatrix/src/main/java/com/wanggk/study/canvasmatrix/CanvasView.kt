package com.wanggk.study.canvasmatrix

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat

/**
 * 2 几何变换
 * 几何变换的使用大概分为三类：
 * 2.1 使用 Canvas 来做常见的二维变换；
 * 2.2 使用 Matrix 来做常见和不常见的二维变换；
 * 2.3 使用 Camera 来做三维变换。
 */
class CanvasView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    init {
        mPaint.color = ContextCompat.getColor(context, R.color.purple_700)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // 2.1 使用 Canvas 来做常见的二维变换：
        canvas.save()
        val bitmap = BitmapFactory.decodeResource(resources,R.drawable.launcher)

        // 2.1.1 Canvas.translate(float dx, float dy) 平移
        // 参数里的 dx 和 dy 表示横向和纵向的位移。
//        canvas.translate(400f, 0f)

        // 2.1.2 Canvas.rotate(float degrees, float px, float py) 旋转
        // 参数里的 degrees 是旋转角度，单位是度（也就是一周有 360° 的那个单位），方向是顺时针为正向； px 和 py 是轴心的位置。
//        canvas.rotate(60f, bitmap.width.toFloat(), bitmap.height.toFloat())

        // 2.1.3 Canvas.scale(float sx, float sy, float px, float py) 放缩
        // 参数里的 sx sy 是横向和纵向的放缩倍数； px py 是放缩的轴心。
//        canvas.scale(1.3f, 1.3f, x + bitmap.width / 2, y + bitmap.height / 2)

        // 2.1.4 skew(float sx, float sy) 错切
        // 参数里的 sx 和 sy 是 x 方向和 y 方向的错切系数。
        canvas.skew(0.5f, 0f)
        canvas.drawBitmap(bitmap, bitmap.width.toFloat(), bitmap.height.toFloat(), mPaint)
        canvas.restore()
    }
}