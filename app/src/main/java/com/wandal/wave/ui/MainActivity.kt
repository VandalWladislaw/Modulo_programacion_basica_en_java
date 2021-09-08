package com.wandal.wave.ui

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.wandal.wave.*
import com.wandal.wave.adapters.ViewPagerAdapter
import com.wandal.wave.databinding.ActivityMainBinding
import com.wandal.wave.interfaces.InterfaceMediaPlayer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_track.*


class MainActivity : AppCompatActivity(), InterfaceMediaPlayer {


    private lateinit var binding: ActivityMainBinding
    private lateinit var mp: MediaPlayer
    private lateinit var dt: MediaMetadataRetriever
    private lateinit var ur: Uri
    private lateinit var hd: Handler
    private var totalTime: Int = 0
    private val adapter by lazy { ViewPagerAdapter(this) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init
        mp = MediaPlayer.create(this, R.raw.concerto)
        dt = MediaMetadataRetriever()
        //dt
        val path = Uri.parse("android.resource://com.wandal.wave/" + R.raw.concerto)
        dt.setDataSource(this,path)

        var album = dt.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
        var track = dt.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        var artist = dt.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)

        pager.adapter = adapter
        val tabLayoutMediator = TabLayoutMediator(tab_layout, pager, TabLayoutMediator.TabConfigurationStrategy{tab, position ->
            when(position){
                0->{
                    tab.text = "Playing"
                    tab.setIcon(R.drawable.ic__wave)
                }
                1->{
                    tab.text = "Playlist"
                    tab.setIcon(R.drawable.ic_list)
                }
                2->{
                    tab.text = "Albums"
                    tab.setIcon(R.drawable.ic__playlist)
                }
                3->{
                    tab.text = "Tracks"
                    tab.setIcon(R.drawable.ic__mnote)
                }
                4->{
                    tab.text = "Folders"
                    tab.setIcon(R.drawable.ic_folder)
                }
            }
        })
        tabLayoutMediator.attach()
        mp.isLooping = false
        totalTime = mp.duration
    }


    override fun getmp(): MediaPlayer {
        return mp
    }

    override fun getdt(): MediaMetadataRetriever {
        return dt
    }

    override fun geturi(): Uri {
        return ur
    }

    override fun mHandler(): Handler {
        return hd
    }

}