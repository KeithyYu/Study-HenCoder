package com.wanggk.study.propertyanimation1

import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.LinearInterpolator
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val iv = findViewById<ImageView>(R.id.iv)
        // ViewPropertyAnimator
        iv.animate().apply {
            startDelay = 1000
            duration = 1000
            interpolator = LinearInterpolator()
//            translationX(500f)
//            translationXBy(500f)
//            translationY(500f)
//            translationZ(500f)
//            x(1000f)
//            y(1000f)
//            rotation(270f)
//            rotationX(180f)
//            rotationY(180f)
//            scaleX(1.5f)
//            scaleY(1.5f)
//            alpha(0.5f)
        }

        val sports = findViewById<SportsView>(R.id.sports)
        val objectAnimator = ObjectAnimator.ofFloat(sports, "process",0f, 65f)
        objectAnimator.duration = 1000
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.start()
    }
}