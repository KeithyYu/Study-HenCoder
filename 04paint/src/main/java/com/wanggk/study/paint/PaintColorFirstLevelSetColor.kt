package com.wanggk.study.paint

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import com.wanggk.study.a04paint.R

/**
 * 像素的基本颜色，根据绘制内容的不同而有不同的控制方式： Canvas 的颜色填充类方法 drawColor/RGB/ARGB() 的颜色，是直接写在方法的参数里，通过参数来设置的（上期讲过了）； drawBitmap() 的颜色，是直接由 Bitmap 对象来提供的（上期也讲过了）；除此之外，是图形和文字的绘制，它们的颜色就需要使用 paint 参数来额外设置了
 */
class PaintColorFirstLevelSetColor(context: Context, attrs:AttributeSet) : View(context, attrs) {

    private var radius: Float = dip2px(context, 40f)

    private var mPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG)

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val centerX: Int = this.width / 2
        val centerY: Int = this.height / 2

        // setColor()
        mPaint.color = Color.parseColor("#FF0000")
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius, mPaint)

        // setARGB()
        mPaint.setARGB(255, 255, 0, 0)
        canvas.drawRect(100f, 100f, 600f, 400f, mPaint)
        mPaint.strokeWidth = dip2px(context, 10f)
        canvas.drawLine(100f,500f,  300f, 800f, mPaint)

        // 在 Android 的绘制里使用 Shader ，并不直接用 Shader 这个类，而是用它的几个子类。
        // 具体来讲有 LinearGradient RadialGradient SweepGradient BitmapShader ComposeShader 这么几个
        // 1. LinearGradient 线性渐变
        // 注意：在设置了 Shader 的情况下， Paint.setColor/ARGB() 所设置的颜色就不再起作用。
        val shader = LinearGradient(100f, 100f, 500f, 500f, Color.parseColor("#E91E63"),
            Color.parseColor("#2196F3"), Shader.TileMode.CLAMP)
        mPaint.setShader(shader)
        canvas.drawCircle(300f, 1200f, 200f, mPaint)

        // 2. RadialGradient 辐射渐变
        val radialGradient = RadialGradient(950f, 250f, radius, Color.parseColor("#E91E63"),Color.parseColor("#2196F3"), Shader.TileMode.CLAMP)
        mPaint.setShader(radialGradient)
        canvas.drawRect(700f, 100f, 1200f, 400f, mPaint)

        // BitmapShader
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.batman)
        val bitmapShader = BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        mPaint.setShader(bitmapShader)
        canvas.drawCircle(400f, 600f, radius, mPaint)

        // ComposeShader 混合着色器, 所谓混合，就是把两个 Shader 一起使用。
        // 第一个 Shader：头像的 Bitmap
        val bitmap1 = BitmapFactory.decodeResource(resources, R.drawable.batman)
        val shader1: Shader = BitmapShader(bitmap1, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        // 第二个 Shader：从上到下的线性渐变（由透明到黑色）
        val bitmap2 = BitmapFactory.decodeResource(resources, R.drawable.batman_logo)
        val shader2: Shader = BitmapShader(bitmap2, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP)
        // ComposeShader：结合两个 Shader
        val composeShader = ComposeShader(shader1, shader2, PorterDuff.Mode.SRC_OVER)
        mPaint.shader = composeShader
        canvas.drawRect(700f, 500f, 1100f, 900f, mPaint)
    }

    private fun dip2px(context: Context, dpValue: Float): Float {
        val scale = context.resources.displayMetrics.density
        return dpValue * scale + 0.5f
    }
}