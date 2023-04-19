package com.example.testovoe3

import Perehod
import Transfer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class TransferAdapter(private val tableData: List<Perehod>, private val count: Int) :
    RecyclerView.Adapter<TransferAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewPlayer: TextView = itemView.findViewById(R.id.textViewPlayer)
        val imageHoz: ImageView = itemView.findViewById(R.id.imageHoz)
        val hozTextView: TextView = itemView.findViewById(R.id.hozTextView)
        val imageStrelka: ImageView = itemView.findViewById(R.id.imageStrelka)
        val gostiTextView: TextView = itemView.findViewById(R.id.gostiTextView)
        val imageGos: ImageView = itemView.findViewById(R.id.imageGos)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.transfer_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val transfer = tableData[position]
        holder.textViewPlayer.text = transfer?.player.toString()
        holder.hozTextView.text = transfer?.from.toString()
        holder.gostiTextView.text = transfer?.to.toString()
        Glide.with(holder.itemView.context)
            .load(transfer?.embFrom)
            .into(holder.imageHoz)
        Glide.with(holder.itemView.context)
            .load(transfer?.embTo)
            .into(holder.imageGos)
        holder.imageStrelka.setBackgroundResource(R.drawable.arrow)
    }

    override fun getItemCount(): Int {
        return count
    }

}