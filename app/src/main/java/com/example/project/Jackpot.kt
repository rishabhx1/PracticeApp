package com.example.project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.ViewFlipper
import androidx.core.view.get
import kotlin.random.Random

class Jackpot : AppCompatActivity() {

    private val random: Random = Random

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jackpot)

        val left : ViewFlipper = findViewById(R.id.left)
        val middle : ViewFlipper = findViewById(R.id.middle)
        val right : ViewFlipper = findViewById(R.id.right)
        val button : Button = findViewById(R.id.go_btn)

        button.setOnClickListener() {
            left.flipInterval = random.nextInt(4, 8) * 50
            left.startFlipping()

            middle.flipInterval = random.nextInt(4, 8) * 50
            middle.startFlipping()

            right.flipInterval = random.nextInt(4, 8) * 50
            right.startFlipping()

            Handler(Looper.getMainLooper()).postDelayed({
                left.stopFlipping()
                middle.stopFlipping()
                right.stopFlipping()
            }, 5000)
        }
    }
}