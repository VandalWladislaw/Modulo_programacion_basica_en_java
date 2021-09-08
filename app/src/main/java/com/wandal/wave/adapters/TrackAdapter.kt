package com.wandal.wave.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.wandal.wave.R
import com.wandal.wave.Track
import com.wandal.wave.data.entities.TrackEntity
import com.wandal.wave.ui.MainActivity

class TrackAdapter(private val tracks:List<Track>) : RecyclerView.Adapter<TrackAdapter.ViewHolder>() {

    private var context: Context? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_custom_row_tracks, viewGroup, false)
        return ViewHolder(v)
    }
    override fun getItemCount(): Int = tracks.size //OK

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        //val item = tracks[i].title
        viewHolder.itemTitle.text = tracks[i].title //OK
        //viewHolder.itemTime.text = duration[i]
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Holds the TextView that will add each item to

        var itemTitle: TextView = itemView.findViewById(R.id.trackTitle)
        //var itemTime: TextView = itemView.findViewById(R.id.trackTime)
        init {
            itemView.setOnClickListener{
                val position: Int = adapterPosition
                val context = itemView.context
                val intent = Intent(context, MainActivity::class.java).apply {
                    //putExtra("NUMBER", position)
                    putExtra("TITLE", itemTitle.text)
                    //putExtra("DURATION", itemTime.text)
                }
                context.startActivity(intent)
            }
        }
    }
}

