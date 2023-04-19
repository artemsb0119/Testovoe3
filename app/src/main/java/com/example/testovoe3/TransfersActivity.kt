package com.example.testovoe3

import Perehod
import Transfer
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.widget.ImageView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.net.URL

class TransfersActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TransferAdapter
    private lateinit var activity: Activity

    private lateinit var imageViewFon2: ImageView

    private var count: Int = 0
    private lateinit var transfer: List<Perehod>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_schedule)
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

        recyclerView = findViewById(R.id.rv_table)
        recyclerView.layoutManager = LinearLayoutManager(this)
        activity = this
        loadData()
    }

    private fun loadData() {
        GlobalScope.launch(Dispatchers.IO) {
            try {
                val data = URL("http://135.181.248.237/3/transfers.json").readText()
                val gson = Gson()
                val tableData = gson.fromJson(data, Array<Transfer>::class.java)

                for (table in tableData) {
                    count = table.count
                    transfer = table.data
                }
                activity.runOnUiThread {
                    adapter = transfer?.let { TransferAdapter(it, count) }!!
                    recyclerView.adapter = adapter
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        startActivity(Intent(this, MenuActivity::class.java))
        finish()
        return super.onKeyDown(keyCode, event)
    }
}