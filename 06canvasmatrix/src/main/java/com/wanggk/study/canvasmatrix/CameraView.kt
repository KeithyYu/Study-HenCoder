package com.wanggk.study.canvasmatrix

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Camera
import android.graphics.Canvas
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
class CameraView(context: Context, attrs:AttributeSet): View(context, attrs) {
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)

    /**
     * 2.3 使用 Camera 来做三维变换
     * Camera 的三维变换有三类：旋转、平移、移动相机。
     */
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.launcher)
        canvas.save()
        // 2.3.1 Camera.rotate*() 三维旋转
        // Camera.rotate*() 一共有四个方法： rotateX(deg) rotateY(deg) rotateZ(deg) rotate(x, y, z)。这四个方法
        // Camera 和 Canvas 一样也需要保存和恢复状态才能正常绘制，不然在界面刷新之后绘制就会出现问题。
        val camera = Camera()
        camera.save()
        camera.rotateX(30f)

//        2.3.2 Camera.translate(float x, float y, float z) 移动
//        它的使用方式和 Camera.rotate*() 相同，而且我在项目中没有用过它，所以就不贴代码和效果图了。

//        2.3.3 Camera.setLocation(x, y, z) 设置虚拟相机的位置
//        注意！这个方法有点奇葩，它的参数的单位不是像素，而是 inch，英寸。
        camera.setLocation(0f, 0f, -10f)

        canvas.translate(bitmap.width.toFloat() / 2, bitmap.height.toFloat() / 2) // 旋转之后把投影移动回来
        camera.applyToCanvas(canvas)
        canvas.translate(-bitmap.width.toFloat() / 2, -bitmap.height.toFloat() / 2) // 旋转之前把绘制内容移动到轴心（原点）
        camera.restore()
        canvas.drawBitmap(bitmap, 0f, 0f, mPaint)
        canvas.restore()
    }
}