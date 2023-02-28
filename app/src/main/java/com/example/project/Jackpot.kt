package com.example.project

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper
import kotlin.random.Random

class Jackpot : AppCompatActivity() {

    private val random: Random = Random
    private var chances = 3
    private lateinit var txtView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jackpot)

        val left : ViewFlipper = findViewById(R.id.left)
        val middle : ViewFlipper = findViewById(R.id.middle)
        val right : ViewFlipper = findViewById(R.id.right)
        val button : Button = findViewById(R.id.go_btn)
        txtView = findViewById(R.id.jck_textview)
        val prevbt : Button = findViewById(R.id.prevbt)

        left.setInAnimation(this, R.anim.slide_in_from_top)

        middle.setInAnimation(this, R.anim.slide_in_from_top)

        right.setInAnimation(this, R.anim.slide_in_from_top)

        txtView.text = "$chances CHANCES LEFT"

        button.setOnClickListener() {
            if(!left.isFlipping && !middle.isFlipping && !right.isFlipping) {
                if(chances > 0) {
                    left.flipInterval = random.nextInt(4, 8) * 50
                    left.startFlipping()

                    middle.flipInterval = random.nextInt(4, 8) * 50
                    middle.startFlipping()

                    right.flipInterval = random.nextInt(4, 8) * 50
                    right.startFlipping()

                    Handler(Looper.getMainLooper()).postDelayed({
                        left.stopFlipping()
                    }, random.nextLong(6,10) * 500)
                    Handler(Looper.getMainLooper()).postDelayed({
                        middle.stopFlipping()
                    }, random.nextLong(6,10) * 500)
                    Handler(Looper.getMainLooper()).postDelayed({
                        right.stopFlipping()
                    }, random.nextLong(6,10) * 500)

                    chances--
                    txtView.text = "$chances CHANCES LEFT"
                } else {
                    Toast.makeText(this@Jackpot, "No more chances!!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        prevbt.setOnClickListener() {
            finish()
        }
    }
    override fun onPause() {
        super.onPause()
        val sharedPreferences : SharedPreferences = getSharedPreferences("ChancesLeft", MODE_PRIVATE)
        val editor : SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("chances", chances)
        editor.apply()
    }

//    override fun onResume() {
//        super.onResume()
//        val sharedPreferences : SharedPreferences = getSharedPreferences("ChancesLeft", MODE_PRIVATE)
//        chances = sharedPreferences.getInt("chances", 3)
//    }
}