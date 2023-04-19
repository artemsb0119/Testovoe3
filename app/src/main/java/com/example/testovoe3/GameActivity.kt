package com.example.testovoe3

import Team
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL
import java.util.*

class GameActivity : AppCompatActivity() {

    private lateinit var imageViewFon2: ImageView

    private lateinit var imageTeam1: ImageView
    private lateinit var imageTeam2: ImageView
    private lateinit var buttonTeam2: AppCompatButton
    private lateinit var buttonTeam1: AppCompatButton
    private lateinit var buttonDraw: AppCompatButton
    private lateinit var textViewScore: TextView
    private lateinit var textViewRes: TextView
    private lateinit var buttonNext: AppCompatButton

    private lateinit var teamData: List<Team>

    private lateinit var activity: Activity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        buttonNext = findViewById(R.id.buttonNext)
        textViewRes = findViewById(R.id.textViewRes)
        textViewScore = findViewById(R.id.textViewScore)
        imageTeam1 = findViewById(R.id.imageTeam1)
        imageTeam2 = findViewById(R.id.imageTeam2)
        buttonTeam2 = findViewById(R.id.buttonTeam2)
        buttonTeam1 = findViewById(R.id.buttonTeam1)
        buttonDraw = findViewById(R.id.buttonDraw)

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
        activity = this
        loadData()

    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data = URL("http://135.181.248.237/3/clubs.json").readText()
                val gson = Gson()
                teamData = gson.fromJson(data, Array<Team>::class.java).toList()
                activity.runOnUiThread {
                    playGame()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun playGame() {
        buttonTeam1.isClickable = true
        buttonTeam2.isClickable = true
        buttonDraw.isClickable = true
        textViewScore.visibility= View.INVISIBLE
        textViewRes.visibility= View.INVISIBLE
        buttonNext.visibility= View.INVISIBLE
        val random = Random()
        val firstTeamIndex = random.nextInt(teamData.size)
        var secondTeamIndex = random.nextInt(teamData.size)
        while (firstTeamIndex == secondTeamIndex) {
            secondTeamIndex = random.nextInt(teamData.size)
        }
        val firstTeam = teamData[firstTeamIndex]
        val secondTeam = teamData[secondTeamIndex]
        Glide.with(activity.applicationContext)
            .load(firstTeam.emb)
            .into(imageTeam1)
        Glide.with(activity.applicationContext)
            .load(secondTeam.emb)
            .into(imageTeam2)
        buttonTeam1.text=firstTeam.team
        buttonTeam2.text=secondTeam.team
        val firstTeamScore = random.nextInt(6) // генерация случайного счета для первой команды
        val secondTeamScore = random.nextInt(6) // генерация случайного счета для второй команды
        val result = when {
            firstTeamScore > secondTeamScore -> "1"
            firstTeamScore < secondTeamScore -> "2"
            else -> "3"
        }
        buttonTeam1.setOnClickListener {
            buttonTeam1.isClickable = false
            buttonTeam2.isClickable = false
            buttonDraw.isClickable = false
            textViewScore.visibility= View.VISIBLE
            textViewRes.visibility= View.VISIBLE
            buttonNext.visibility= View.VISIBLE
            val guessResult = if (result == "1") "CONGRATULATE!" else "NO LUCK"
            if (guessResult.equals("CONGRATULATE!")){
                textViewScore.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.green))
                textViewRes.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.green))
            } else {
                textViewScore.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.red))
                textViewRes.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.red))
            }
            textViewScore.text = "$firstTeamScore:$secondTeamScore"
            textViewRes.text = "$guessResult"
        }
        buttonTeam2.setOnClickListener {
            buttonTeam1.isClickable = false
            buttonTeam2.isClickable = false
            buttonDraw.isClickable = false
            textViewScore.visibility= View.VISIBLE
            textViewRes.visibility= View.VISIBLE
            buttonNext.visibility= View.VISIBLE
            val guessResult = if (result == "1") "CONGRATULATE!" else "NO LUCK"
            if (guessResult.equals("CONGRATULATE!")){
                textViewScore.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.green))
                textViewRes.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.green))
            } else {
                textViewScore.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.red))
                textViewRes.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.red))
            }
            textViewScore.text = "$firstTeamScore:$secondTeamScore"
            textViewRes.text = "$guessResult"
        }
        buttonDraw.setOnClickListener {
            buttonTeam1.isClickable = false
            buttonTeam2.isClickable = false
            buttonDraw.isClickable = false
            textViewScore.visibility= View.VISIBLE
            textViewRes.visibility= View.VISIBLE
            buttonNext.visibility= View.VISIBLE
            val guessResult = if (result == "1") "CONGRATULATE!" else "NO LUCK"
            if (guessResult.equals("CONGRATULATE!")){
                textViewScore.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.green))
                textViewRes.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.green))
            } else {
                textViewScore.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.red))
                textViewRes.setTextColor(ContextCompat.getColor(activity.applicationContext, R.color.red))
            }
            textViewScore.text = "$firstTeamScore:$secondTeamScore"
            textViewRes.text = "$guessResult"
        }
        buttonNext.setOnClickListener {
            playGame()
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
        return super.onKeyDown(keyCode, event)
    }
}