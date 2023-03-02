package com.example.project

import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import android.widget.ViewFlipper
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class Jackpot : AppCompatActivity() {

    private val random: Random = Random
    private lateinit var txtView: TextView
    private lateinit var resTxt: TextView

    private val sharedPreferences: SharedPreferences by lazy {
        getSharedPreferences("ChancesLeft", MODE_PRIVATE)
    }

    private val left: ViewFlipper by lazy {
        findViewById(R.id.left)
    }

    private val middle: ViewFlipper by lazy {
        findViewById(R.id.middle)
    }

    private val right: ViewFlipper by lazy {
        findViewById(R.id.right)
    }

    private var chances: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_jackpot)

        chances = sharedPreferences.getInt("chances", 3)
        val button: Button = findViewById(R.id.go_btn)
        txtView = findViewById(R.id.jck_textview)
        resTxt = findViewById(R.id.res_text)
        val prevbt: Button = findViewById(R.id.prevbt)

        left.setInAnimation(this, R.anim.slide_in_from_top)

        middle.setInAnimation(this, R.anim.slide_in_from_top)

        right.setInAnimation(this, R.anim.slide_in_from_top)

        txtView.text = "$chances CHANCES LEFT"

        button.setOnClickListener {
            if (!left.isFlipping && !middle.isFlipping && !right.isFlipping) {
                if (chances > 0) {
                    startFlipping(left)
                    startFlipping(middle)
                    startFlipping(right)

                    stopFlipping(left)
                    stopFlipping(middle)
                    stopFlipping(right)

                    chances--
                    txtView.text = "$chances CHANCES LEFT"
                } else {
                    Toast.makeText(this@Jackpot, "No more chances!!", Toast.LENGTH_SHORT).show()
                }
            }
        }

        prevbt.setOnClickListener {
            finish()
        }
    }

    private fun stopFlipping(viewFlipper: ViewFlipper) {
        val time = random.nextLong(6, 10) * 500
        Handler(Looper.getMainLooper()).postDelayed({
            viewFlipper.stopFlipping()
            if (!left.isFlipping && !middle.isFlipping && !right.isFlipping) {
                if(left.displayedChild == middle.displayedChild && left.displayedChild == right.displayedChild) {
                    resTxt.text = "JACKPOT"
                }
            }
        }, time)
    }

    private fun startFlipping(viewFlipper: ViewFlipper) {
        viewFlipper.flipInterval = random.nextInt(4, 8) * 50
        viewFlipper.startFlipping()
    }

    override fun onStop() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putInt("chances", chances)
        editor.apply()
        super.onStop()
    }
}