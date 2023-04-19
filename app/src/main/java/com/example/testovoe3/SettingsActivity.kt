package com.example.testovoe3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatButton
import com.bumptech.glide.Glide
import kotlin.random.Random

class SettingsActivity : AppCompatActivity() {

    private lateinit var imageViewFon2: ImageView
    private lateinit var buttonChange: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        imageViewFon2 = findViewById(R.id.imageViewFon2)
        buttonChange = findViewById(R.id.buttonChange)
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

        buttonChange.setOnClickListener {
            val min = if (currentNumber == 1) 2 else 1
            val max = if (currentNumber == 3) 2 else 3

            currentNumber = Random.nextInt(min, max + 1)
            val editor = getSharedPreferences("myPrefs", Context.MODE_PRIVATE).edit()
            editor.putInt("currentNumber", currentNumber)
            editor.apply()
            recreate()
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
        return super.onKeyDown(keyCode, event)
    }
}