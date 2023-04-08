package com.wanggk.study.propertyanimation1

import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.animation.PointFEvaluator
import android.animation.PropertyValuesHolder
import android.animation.ValueAnimator.ofArgb
import android.graphics.Color
import android.graphics.Path
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import android.widget.ImageView
import androidx.interpolator.view.animation.FastOutLinearInInterpolator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iv = findViewById<ImageView>(R.id.iv)
        // ViewPropertyAnimator
        iv.animate().apply {
            startDelay = 1000
            duration = 1000

            // 匀速
//            interpolator = LinearInterpolator()

            // 先加速再减速
//            interpolator = AccelerateDecelerateInterpolator()

            // 持续加速
//            interpolator = AccelerateInterpolator()

            // 持续减速
//            interpolator = DecelerateInterpolator()

            // 先回拉一下再进行正常动画轨迹。效果看起来有点像投掷物体或跳跃等动作前的蓄力。
//            interpolator = AnticipateInterpolator()

            // 动画会超过目标值一些，然后再弹回来。效果看起来有点像你一屁股坐在沙发上后又被弹起来一点的感觉。
//            interpolator = OvershootInterpolator()

            // 开始前回拉，最后超过一些然后回弹。
//            interpolator = AnticipateOvershootInterpolator()

            // 在目标值处弹跳。有点像玻璃球掉在地板上的效果。
//            interpolator = BounceInterpolator()

            // 这个也是一个正弦 / 余弦曲线，不过它和 AccelerateDecelerateInterpolator 的区别是，它可以自定义曲线的周期，所以动画可以不到终点就结束，也可以到达终点后回弹，回弹的次数由曲线的周期决定，曲线的周期由 CycleInterpolator() 构造方法的参数决定。
//            interpolator = CycleInterpolator(2f)

            val interpolatorPath = Path()
            // 匀速
//            interpolatorPath.lineTo(1f, 1f)

//            // 先以「动画完成度 : 时间完成度 = 1 : 1」的速度匀速运行 25%
//            interpolatorPath.lineTo(0.25f, 0.25f);
//            // 然后瞬间跳跃到 150% 的动画完成度
//            interpolatorPath.moveTo(0.25f, 1.5f);
//            // 再匀速倒车，返回到目标点
//            interpolatorPath.lineTo(1f, 1f);
//            interpolator = PathInterpolator(interpolatorPath)

            // 加速运动
            interpolator = FastOutLinearInInterpolator()

//            translationX(500f)
//            translationXBy(500f)
//            translationY(500f)
//            translationZ(500f)
//            x(1000f)
//            y(1000f)
//            rotation(270f)
//            rotationX(180f)
//            rotationY(180f)
            scaleX(0.5f)
            scaleY(0.5f)
            alpha(0.5f)
        }

        val sports = findViewById<SportsView>(R.id.sports)
        val objectAnimator = ObjectAnimator.ofFloat(sports, "process",0f, 65f)
        objectAnimator.duration = 1000
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.start()

        val circleView = findViewById<CircleView>(R.id.circle_view)
//        setCircleAnimator(circleView)
//        setCircleColorByHsv(circleView)
//        setCirclePointF(circleView)
        setCircleAnimatorByPropertyViewHolder(circleView)
    }

    private fun setCircleAnimatorByPropertyViewHolder(circleView: CircleView?) {
        val holder1 = PropertyValuesHolder.ofInt("color", Color.BLUE, Color.RED)
        holder1.setEvaluator(HsvEvaluator())
        val holder2 = PropertyValuesHolder.ofObject("position",PointFEvaluator(), PointF(0f, 0f), PointF(1f, 1f))
        val animator = ObjectAnimator.ofPropertyValuesHolder(circleView, holder1, holder2)
        animator.duration = 2000
        animator.interpolator = LinearInterpolator()
        animator.start()
    }

    private fun setCircleAnimator(circleView : CircleView) {
        val objectAnimator = ObjectAnimator.ofArgb(circleView, "color", Color.BLUE, Color.RED)
        objectAnimator.duration = 1000
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.start()
    }

    private fun setCircleColorByHsv(circleView : CircleView){
        val objectAnimator = ObjectAnimator.ofInt(circleView, "color", Color.RED, Color.GREEN)
        objectAnimator.setEvaluator(HsvEvaluator())
        objectAnimator.duration = 2000
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.start()
    }

    private fun setCirclePointF(circleView : CircleView) {
        val objectAnimator = ObjectAnimator.ofObject(circleView, "position",PointFEvaluator(), PointF(0f, 0f), PointF(1f, 1f))
        objectAnimator.duration = 1000
        objectAnimator.start()
    }
}