package com.example.testovoe3

import Matchs
import android.graphics.Typeface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ScheduleAdapter(private val tableData: List<Matchs>, private val count: Int) : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewData: TextView = itemView.findViewById(R.id.textViewData)
        val imageHoz: ImageView = itemView.findViewById(R.id.imageHoz)
        val hozTextView: TextView = itemView.findViewById(R.id.hozTextView)
        val tire: TextView = itemView.findViewById(R.id.tire)
        val gostiTextView: TextView = itemView.findViewById(R.id.gostiTextView)
        val imageGos: ImageView = itemView.findViewById(R.id.imageGos)
        val vremyaTextView: TextView = itemView.findViewById(R.id.vremyaTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.match_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when (position) {
            0 ->{
                holder.textViewData.text = "Date"
                holder.hozTextView.text = "Home"
                holder.gostiTextView.text = "Guests"
                holder.vremyaTextView.text = "Time"
                holder.tire.text = "-"
                Glide.with(holder.itemView.context)
                    .load("")
                    .into(holder.imageHoz)
                Glide.with(holder.itemView.context)
                    .load("")
                    .into(holder.imageGos)
            }

            else -> {
                val matchs = tableData[position - 1]
                holder.textViewData.text = matchs?.data.toString()
                holder.hozTextView.text = matchs?.hoz.toString()
                holder.gostiTextView.text = matchs?.gos.toString()
                holder.vremyaTextView.text = matchs?.vremya.toString()
                holder.tire.text = "-"
                Glide.with(holder.itemView.context)
                    .load(matchs?.embHoz)
                    .into(holder.imageHoz)
                Glide.with(holder.itemView.context)
                    .load(matchs?.embGos)
                    .into(holder.imageGos)
            }
        }
    }

    override fun getItemCount(): Int {
        return count+1
    }

}