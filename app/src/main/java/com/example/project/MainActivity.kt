package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Im
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import java.util.Random
import kotlin.text.Typography.degree

class MainActivity : AppCompatActivity() {

    private val points = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12")
    private val pointRange = arrayOfNulls<Int>(points.size)
    private val random: Random = Random()
    private var degree = 0
    private var isSpinning = false
    private var chances = 3

    private lateinit var wheel : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getPointRangeOnWheel()

        val button: Button = findViewById(R.id.button)
        val textView: TextView = findViewById(R.id.chances)
        textView.text = "$chances CHANCES LEFT"
        wheel = findViewById(R.id.imageView)
        wheel.rotation = -15f

        button.setOnClickListener() {
            if(!isSpinning) {
                if(chances > 0) {
                    spin()
                    isSpinning = true
                    chances--
                    textView.text = "$chances CHANCES LEFT"
                } else {
                    Toast.makeText(this@MainActivity, "No more chances!!", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun spin() {
        degree = random.nextInt(points.size - 1)

        val rotateAnimation =
            RotateAnimation(0F, ((360 * points.size) + pointRange[degree]!!).toFloat(),
            RotateAnimation.RELATIVE_TO_SELF, 0.5f,
            RotateAnimation.RELATIVE_TO_SELF, 0.5f)

        rotateAnimation.duration = 3600
        rotateAnimation.fillAfter = true
        rotateAnimation.interpolator = DecelerateInterpolator()
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                Toast.makeText(this@MainActivity, points[points.size - (degree + 1)], Toast.LENGTH_SHORT).show()
                isSpinning = false
            }

            override fun onAnimationRepeat(animation: Animation?) {}

        })

        wheel.startAnimation(rotateAnimation)

    }

    private fun getPointRangeOnWheel() {
        val pointAngle = 360/points.size
        for (i in points.indices) {
            pointRange[i] = (i + 1) * pointAngle
        }
    }
}