package com.example.testovoe3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide
import java.time.LocalDateTime

class MenuActivity : AppCompatActivity() {
    private lateinit var buttonSchedule: AppCompatButton
    private lateinit var buttonGuess: AppCompatButton
    private lateinit var buttonTransfers: AppCompatButton
    private lateinit var buttonSettings: AppCompatButton
    private lateinit var imageViewFon2: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        buttonSchedule = findViewById(R.id.buttonSchedule)
        buttonGuess = findViewById(R.id.buttonGuess)
        buttonTransfers = findViewById(R.id.buttonTransfers)
        buttonSettings = findViewById(R.id.buttonSettings)
        imageViewFon2 = findViewById(R.id.imageViewFon2)

        val prefs = getSharedPreferences("myPrefs", Context.MODE_PRIVATE)
        var currentNumber = prefs.getInt("currentNumber", 1)
        if(currentNumber==1) {
            Glide.with(this)
                .load("http://135.181.248.237/3/fon2.png")
                .into(imageViewFon2)
        } else if (currentNumber==2){
            Glide.with(this)
                .load("http://135.181.248.237/3/fon3.png")
                .into(imageViewFon2)
        } else if (currentNumber==3){
            Glide.with(this)
                .load("http://135.181.248.237/3/fon4.png")
                .into(imageViewFon2)
        }

        buttonSchedule.setOnClickListener {
            val intent = Intent(this, ScheduleActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonGuess.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonTransfers.setOnClickListener {
            val intent = Intent(this, TransfersActivity::class.java)
            startActivity(intent)
            finish()
        }
        buttonSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}