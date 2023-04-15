package com.wanggk.study.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.wanggk.study.a04paint.R


/**
 * ColorFilter 这个类，它的名字已经足够解释它的作用：为绘制设置颜色过滤。
 * 颜色过滤的意思，就是为绘制的内容设置一个统一的过滤策略，然后 Canvas.drawXXX() 方法会对每个像素都进行过滤后再绘制出来。
 */
class PaintColorSecondLevelColorFilter(context: Context, attrs: AttributeSet) : View(context, attrs) {
    val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    val raduis = dip2px(context, 40f)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        /**
         * 在 Paint 里设置 ColorFilter ，使用的是 Paint.setColorFilter(ColorFilter filter) 方法。
         * ColorFilter 并不直接使用，而是使用它的子类。它共有三个子类：LightingColorFilter PorterDuffColorFilter 和 ColorMatrixColorFilter。
         */
        // 1.2.1 LightingColorFilter 这个 LightingColorFilter 是用来模拟简单的光照效果的。
        // 如果你想去掉原像素中的红色，可以把它的 mul 改为 0x00ffff （红色部分为 0 ）
//        val lightingColorFilter = LightingColorFilter(0x00ffff, 0x000000)
//        mPaint.colorFilter = lightingColorFilter
        // 如果你想让它的绿色更亮一些，就可以把它的 add 改为 0x003000 （绿色部分为 0x30 ）
        val lightingColorFilter2 = LightingColorFilter(0xffffff, 0x005000)
        mPaint.colorFilter = lightingColorFilter2
        mPaint.setColor(Color.parseColor("#DFE1DE"))
        canvas.drawRect(100f, 100f, 600f, 500f, mPaint)

        // 1.2.2 PorterDuffColorFilter
        val porterDuffColorFilter = PorterDuffColorFilter(0xFF0000, PorterDuff.Mode.SRC_OVER)
        mPaint.colorFilter = porterDuffColorFilter
        mPaint.color = ContextCompat.getColor(context, R.color.teal_200)
        canvas.drawCircle(800f, 300f, raduis, mPaint)

        // 1.2.3 ColorMatrixColorFilter
        // ColorMatrixColorFilter 使用一个 ColorMatrix 来对颜色进行处理。 ColorMatrix 这个类，内部是一个 4x5 的矩阵
        // 暂无例子

    }

    private fun dip2px(context: Context, dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }
}