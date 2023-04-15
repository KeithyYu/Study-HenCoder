package com.wanggk.study.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * "Xfermode" 其实就是 "Transfer mode"，用 "X" 来代替 "Trans" 是一些美国人喜欢用的简写方式。
 * 严谨地讲， Xfermode 指的是你要绘制的内容和 Canvas 的目标位置的内容应该怎样结合计算出最终的颜色。
 * 但通俗地说，其实就是要你以绘制的内容作为源图像，以 View 中已有的内容作为目标图像，选取一个 PorterDuff.Mode 作为绘制内容的颜色处理方案。
 */
class PaintColorThirdLevelXfermode(context: Context, attrs:AttributeSet) : View(context, attrs) {
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val xfermode = PorterDuffXfermode(PorterDuff.Mode.DST_IN)
//        canvas.drawBitmap(rectBitmap, 0, 0, mPaint); // 画方
//        mPaint.setXfermode(xfermode); // 设置 Xfermode
//        canvas.drawBitmap(circleBitmap, 0, 0, mPaint); // 画圆
//        mPaint.setXfermode(null); // 用完及时清除 Xfermode
    }
}