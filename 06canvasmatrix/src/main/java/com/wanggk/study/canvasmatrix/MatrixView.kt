package com.wanggk.study.canvasmatrix

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * 2 几何变换
 * 几何变换的使用大概分为三类：
 * 2.1 使用 Canvas 来做常见的二维变换；
 * 2.2 使用 Matrix 来做常见和不常见的二维变换；
 * 2.3 使用 Camera 来做三维变换。
 */
class MatrixView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.launcher)
        val bitmapWidth = bitmap.width
        val bitmapHeight = bitmap.height
//        2.2 使用 Matrix 来做变换
//        2.2.1 使用 Matrix 来做常见变换
//        Matrix 做常见变换的方式：
//
//        1.创建 Matrix 对象；
//        2.调用 Matrix 的 pre/postTranslate/Rotate/Scale/Skew() 方法来设置几何变换；
//        3.使用 Canvas.setMatrix(matrix) 或 Canvas.concat(matrix) 来把几何变换应用到 Canvas。

        canvas.save()
        val matrix = Matrix()
        matrix.postTranslate(bitmapWidth.toFloat(), 0f)
        matrix.preRotate(60f)
        canvas.concat(matrix)
        canvas.drawBitmap(bitmap, (bitmapWidth / 2).toFloat(), (bitmapHeight / 2).toFloat(), mPaint)

        canvas.restore()
    }
}