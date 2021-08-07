package com.wandal.wave.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wandal.wave.R
import com.wandal.wave.ui.MainActivity

class PlaylistAdapter : RecyclerView.Adapter<PlaylistAdapter.ViewHolder>() {

    val title = arrayOf("Finnish", "Franzoesisch", "Italienisch", "Russisch")

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_custom_row, viewGroup, false)
        return ViewHolder(v)

    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {

        viewHolder.itemTitle.text = title[i]

    }

    override fun getItemCount(): Int {
        return title.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Holds the TextView that will add each item to

        var itemTitle: TextView

        init {
            itemTitle = itemView.findViewById(R.id.title)

            itemView.setOnClickListener{
                val position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, MainActivity::class.java).apply {
                    putExtra("NUMBER", position)
                    putExtra("TITLE", itemTitle.text)
                }
                context.startActivity(intent)
            }
        }
    }
}

