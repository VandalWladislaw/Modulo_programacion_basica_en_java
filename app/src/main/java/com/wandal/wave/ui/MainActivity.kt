package com.wandal.wave.ui

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.tabs.TabLayoutMediator
import com.wandal.wave.R
import com.wandal.wave.adapters.ViewPagerAdapter
import com.wandal.wave.interfaceMediaPlayer
import com.wandal.wave.utilities.InjectorUtils
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), interfaceMediaPlayer {
    private lateinit var mp: MediaPlayer
    private lateinit var dt: MediaMetadataRetriever

    private var totalTime: Int = 0
    private val adapter by lazy { ViewPagerAdapter(this) }

       override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mp = MediaPlayer.create(this, R.raw.concerto)


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

        //position bar

        //nunca se enlaza seekbar, pendiente


        //Thread
        /*Thread(Runnable{
            while(mp != null){
                try{
                    var msg = Message()
                    msg.what = mp.currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException){
                    //nada
                }
            }
        }).start()*/
    }

    /*@SuppressLint("HandlerLeak")
    var handler = object : Handler(){
        override fun handleMessage(msg: Message){
            var currentPosition = msg.what

            //update positionBar
            positionBar.progress = currentPosition

            //update labels
            var elapsedTime = createTimeLabel(currentPosition)
            textElapsed.text = elapsedTime

            var remainingTime = createTimeLabel(totalTime - currentPosition)
            textRemaining.text = "-$remainingTime"
        }
    }*/

    /*fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if(sec<10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }*/

    private fun initializeUi(){
        val factory = InjectorUtils.provideViewModelFactory()
        val viewModel = ViewModelProviders.of(this, factory)
            .get(ViewModel::class.java)

        viewModel.getWaves().observe(this, Observer{waves ->
            val stringBuilder = StringBuilder()
            waves.forEach{ wave ->
                stringBuilder.append("$wave\n\n")
            }
        })
    }

    override fun getmp(): MediaPlayer {
        return mp
    }

    override fun getdt(): MediaMetadataRetriever {
        return dt
    }

}