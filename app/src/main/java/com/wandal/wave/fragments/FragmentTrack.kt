package com.wandal.wave.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wandal.wave.R
import com.wandal.wave.adapters.TrackAdapter
import com.wandal.wave.ui.ViewModelTrack
import kotlinx.android.synthetic.main.fragment_track.*

class FragmentTrack : Fragment() {

    private lateinit var viewModelTrack: ViewModelTrack
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var adapter: RecyclerView.Adapter<TrackAdapter.ViewHolder>? = null
    private val trackData = mutableListOf<String>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //this.viewModelTrack = ViewModelProvider(this).get(ViewModelTrack::class.java)
        return inflater.inflate(R.layout.fragment_track, container, false)
    }

    override fun onViewCreated(itemView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(itemView, savedInstanceState)
        this.viewModelTrack = ViewModelProvider(this).get(ViewModelTrack::class.java)
        this.viewModelTrack.tracks.observe(viewLifecycleOwner, Observer {
            recyclerTracks.apply {
                layoutManager = LinearLayoutManager(activity)
                adapter = TrackAdapter(it)
            }
        })
    }
}