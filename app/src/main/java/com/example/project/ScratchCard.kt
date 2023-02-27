package com.example.project

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cooltechworks.views.ScratchTextView


class ScratchCard : AppCompatActivity() {

    lateinit var scratchTextView: ScratchTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scratch_card)

        scratchTextView = findViewById(R.id.scratchTextView)
        val prev : Button = findViewById(R.id.prev)
        val next : Button = findViewById(R.id.next)
        scratchTextView.setRevealListener(object : ScratchTextView.IRevealListener {
            override fun onRevealed(tv: ScratchTextView?) {

            }

            override fun onRevealPercentChangedListener(stv: ScratchTextView?, percent: Float) {
                if(percent >= 0.7) {
                    stv?.reveal()
                }
            }
        })

        prev.setOnClickListener() {
            finish()
        }

        next.setOnClickListener() {
            val intent = Intent(this, Jackpot::class.java)
            startActivity(intent)
        }
    }
}