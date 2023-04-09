package com.wanggk.study.layout

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * 来源：https://hencoder.com/ui-2-1/
 */
class SquareImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        // 先获取原生的测量结果，此时他已经将测量的结果保存了
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        // 获取已经测量完并且保存了的测量(ImageView预期)结果
        val size = Math.max(measuredWidth, measuredHeight)

        // 根据实际尺寸父View给出的尺寸限制，从何获取到最合适的尺寸大小
        val resolveSize = resolveSize(size, widthMeasureSpec)

        // 将重置之后的数据保存在View自身
        setMeasuredDimension(resolveSize, resolveSize)
    }

    private fun dip2px(context: Context, dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }
}