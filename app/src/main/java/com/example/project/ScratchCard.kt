package com.example.project

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.cooltechworks.views.ScratchTextView


class ScratchCard : AppCompatActivity() {

    lateinit var scratchTextView: ScratchTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scratch_card)

        scratchTextView = findViewById(R.id.scratchTextView)
        scratchTextView.setRevealListener(object : ScratchTextView.IRevealListener {
            override fun onRevealed(tv: ScratchTextView?) {
            }

            override fun onRevealPercentChangedListener(stv: ScratchTextView?, percent: Float) {

            }
        })
    }
}