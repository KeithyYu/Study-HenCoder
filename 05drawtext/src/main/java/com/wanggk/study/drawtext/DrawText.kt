package com.wanggk.study.drawtext

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import java.util.*

/**
 * Canvas 绘制文字的方式
 */
class DrawText(context: Context, attrs: AttributeSet) : View(context, attrs) {
    companion object {
        private val TAG = DrawText::class.java.simpleName
    }

    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG and Paint.DITHER_FLAG)

    private val mRadius = dip2px(context, 20f)

    init {
        mPaint.color = Color.BLUE
        // 设置字体大小
        mPaint.textSize = dip2px(context, 30f)

        // 设置字体。
        mPaint.typeface = Typeface.SERIF

        // 是否使用伪粗体, 之所以叫伪粗体（ fake bold ），因为它并不是通过选用更高 weight 的字体让文字变粗，而是通过程序在运行时把文字给「描粗」了。
        mPaint.isFakeBoldText = true

        // 是否加删除线。
        mPaint.isStrikeThruText = true

        // 是否加下划线
        mPaint.isUnderlineText = true

        // 设置文字横向错切角度。其实就是文字倾斜度的啦。
        mPaint.textSkewX = -0.5f

        // 设置文字横向放缩。也就是文字变胖变瘦。
//        mPaint.textScaleX = 1.5f

        // 设置字符间距。默认值是 0。
        mPaint.letterSpacing = 0.2f

        // 用 CSS 的 font-feature-settings 的方式来设置文字。
        mPaint.fontFeatureSettings = "small caps"

//        mPaint.getTextWidths(TAG, FloatArray())
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mPaint.setShadowLayer(10f, 0f,0f, Color.RED)

        val centerX = width / 8
        val centerY = height / 2
        // 1.1 drawText(String text, float x, float y, Paint paint)
        // 用来让所有文字互相对齐的基准线，就是基线( baseline )。 drawText() 方法参数中的 y 值，就是指定的基线的位置。
        canvas.drawText(TAG, centerX.toFloat(), centerY.toFloat(), mPaint)

        // setTextAlign 设置文字的对齐方式。一共有三个值：LEFT CETNER 和 RIGHT。默认值为 LEFT
        // setTextLocale 设置绘制所使用的 Locale, Locale 直译是「地域」，其实就是你在系统里设置的「语言」或「语言区域」（具体名称取决于你用的是什么手机），比如「简体中文（中国）」「English (US)」「English (UK)」
//        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setTextLocale(Locale.CHINA); // 简体中文
        canvas.drawText("设置绘制所使用的地域", 50F, 150F, mPaint)
//        mPaint.setTextAlign(Paint.Align.CENTER)
        mPaint.textLocale = Locale.TAIWAN
        canvas.drawText("设置绘制所使用的地域", 50F, 150 + 150F, mPaint)
//        mPaint.setTextAlign(Paint.Align.RIGHT)
        mPaint.textLocale = Locale.JAPAN
        canvas.drawText("设置绘制所使用的地域", 50f, 150 + 150f * 2, mPaint)

        // 1.2 drawTextRun()
        // 声明：这个方法对中国人没用。所以如果你有兴趣，可以继续看；而如果你想省时间，直接跳过这个方法看后面的就好了，没有任何毒副作用。

        // 1.3 drawTextOnPath()
        // 沿着一条 Path 来绘制文字。这是一个耍杂技的方法。
//        val path = Path()
//        path.lineTo(200f, 200f) // lineTo
//        path.lineTo(200f, 0f)
//        mPaint.strokeJoin = Paint.Join.ROUND
//        canvas.drawPath(path, mPaint) // 把 Path 也绘制出来，理解起来更方便
//        canvas.drawTextOnPath("Hello HeCoder", path, 0f, 0f, mPaint);
    }

    private fun dip2px(context: Context, dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }
}