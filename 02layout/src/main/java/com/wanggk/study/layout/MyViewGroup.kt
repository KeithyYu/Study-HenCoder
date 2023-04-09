package com.wanggk.study.layout

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup

/**
 * 来源：https://rengwuxian.com/ui-2-3/
 */
class MyViewGroup(context: Context, attrs: AttributeSet): ViewGroup(context, attrs) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        var usedWidthSize = 0
        var usedHeightSize = 0

        // 遍历测量所有的子View的尺寸
        for (index in 0 until childCount) {
            val childView = getChildAt(index)

            // 获取布局文件中开发者设置的layout_开头的布局
            val lp = childView.layoutParams

            val widthMode = MeasureSpec.getMode(widthMeasureSpec)
            val measureWidthSize = MeasureSpec.getSize(widthMeasureSpec)

            val heightMode = MeasureSpec.getMode(heightMeasureSpec)
            val measureHeightSize = MeasureSpec.getSize(heightMeasureSpec)

            // 根据开发者布局的要求和本身ViewGroup的父类对本ViewGroup尺寸的限制综合获得本ViewGroup的子View的测量结果
            // 获取子View的宽度的measureSpec为调用子类的measure方法的参数做准备
            val childWidthMeasureSpec =  when(lp.width) {
                LayoutParams.MATCH_PARENT -> {
                    if (widthMode == MeasureSpec.AT_MOST || widthMode == MeasureSpec.EXACTLY) {
                        MeasureSpec.makeMeasureSpec(measureWidthSize - usedWidthSize, MeasureSpec.EXACTLY)
                    } else {
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    }
                }
                LayoutParams.WRAP_CONTENT -> {
                    if (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST) {
                        MeasureSpec.makeMeasureSpec(measureWidthSize - usedWidthSize, MeasureSpec.AT_MOST)
                    } else {
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    }
                }
                else -> {
                    MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY)
                }
            }

            // 同理获取子View的高度的measureSpec为调用子类的measure方法的参数做准备
            val childHeightMeasureSpec = when(lp.height) {
                LayoutParams.MATCH_PARENT -> {
                    if (heightMode == MeasureSpec.AT_MOST || heightMode == MeasureSpec.EXACTLY) {
                        MeasureSpec.makeMeasureSpec(measureHeightSize - usedHeightSize, MeasureSpec.EXACTLY)
                    } else {
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    }
                }
                LayoutParams.WRAP_CONTENT -> {
                    if (heightMode == MeasureSpec.EXACTLY || heightMode == MeasureSpec.AT_MOST) {
                        MeasureSpec.makeMeasureSpec(measureHeightSize - usedHeightSize, MeasureSpec.AT_MOST)
                    } else {
                        MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED)
                    }
                }
                else -> {
                    MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.EXACTLY)
                }
            }

            childView.measure(childWidthMeasureSpec, childHeightMeasureSpec)
            usedWidthSize += childView.measuredWidth
            usedHeightSize += childView.measuredHeight
        }

        // 将最终的计算结果保存
        setMeasuredDimension(usedWidthSize, usedHeightSize)
    }

    override fun onLayout(changed: Boolean, l: Int, t: Int, r: Int, b: Int) {
        // 遍历所有的子View 的Layout进行界面的布局
        for (index in 0 until childCount) {
            val childView = getChildAt(index)
            childView.layout(l, t, l + childView.measuredWidth, t + childView.measuredHeight)
        }
    }
}