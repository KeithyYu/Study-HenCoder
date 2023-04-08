package com.wanggk.study.propertyanimation1

import android.animation.TypeEvaluator
import android.graphics.Color

class HsvEvaluator : TypeEvaluator<Int> {
    val startHsv = FloatArray(3)
    val endHsv = FloatArray(3)
    val outHsv = FloatArray(3)
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        // 把 ARGB 转换成 HSV
        Color.colorToHSV(startValue, startHsv)
        Color.colorToHSV(endValue, endHsv)

        // 计算当前动画完成度（fraction）所对应的颜色值
        if (endHsv[0] - startHsv[0] > 180) {
            endHsv[0] -= 360f
        } else if (endHsv[0] - startHsv[0] < -180) {
            endHsv[0] += 360f
        }
        outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction
        if (outHsv[0] > 360) {
            outHsv[0] -= 360f
        } else if (outHsv[0] < 0) {
            outHsv[0] += 360f
        }
        outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction
        outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction

        // 计算当前动画完成度（fraction）所对应的透明度
        // 计算当前动画完成度（fraction）所对应的透明度
        val alpha = startValue shr 24 + ((endValue shr 24 - startValue shr 24) * fraction).toInt()

        return Color.HSVToColor(alpha, outHsv)
    }
}