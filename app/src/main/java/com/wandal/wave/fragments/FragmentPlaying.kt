package com.wandal.wave.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.media.MediaDataSource
import android.media.MediaMetadata
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import com.wandal.wave.interfaces.InterfaceMediaPlayer
import com.wandal.wave.R
import com.wandal.wave.databinding.FragmentPlayingBinding

class FragmentPlaying : Fragment() {

    private var _binding: FragmentPlayingBinding? = null
    private val binding get() = _binding!!
    private var totalTime: Int = 0
    private lateinit var actividad: Activity
    lateinit var interfaceMediaPlayer: InterfaceMediaPlayer

    //dev.to/gvetri/que-es-android-jetpack-5g3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        totalTime = interfaceMediaPlayer.getmp().duration
        interfaceMediaPlayer.getmp().isLooping = false
        }
    fun play() {
        interfaceMediaPlayer.getmp().start()
        binding.buttonPlayback.setBackgroundResource(R.drawable.ic_pause)
    }
    fun pause() {
        interfaceMediaPlayer.getmp().pause()
        binding.buttonPlayback.setBackgroundResource(R.drawable.ic_play)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPlayingBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        binding.textTrack.text = interfaceMediaPlayer.getdt().extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)
        binding.textArtist.text = interfaceMediaPlayer.getdt().extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        binding.textAlbum.text = interfaceMediaPlayer.getdt().extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)
        binding.buttonPlayback.setOnClickListener {
            if(interfaceMediaPlayer.getmp().isPlaying){
                pause()
            } else{
                play()
            }
        }
        binding.positionBar.max = interfaceMediaPlayer.getmp().duration
        binding.positionBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){
                if(fromUser){
                    interfaceMediaPlayer.getmp().seekTo(progress)
                    binding.positionBar.progress = progress
                }
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
        Thread(Runnable {
            while (interfaceMediaPlayer.getmp() != null) {
                try {
                    var msg = Message()
                    msg.what = interfaceMediaPlayer.getmp().currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)
                } catch (e: InterruptedException) {
                }
            }
        }).start()
    }
    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            var currentPosition = msg.what
            binding.positionBar.progress = currentPosition
            var elapsedTime = createTimeLabel(currentPosition)
            binding.textElapsed.text = elapsedTime
            var remainingTime = createTimeLabel(totalTime - currentPosition)
            binding.textRemaining.text = "-$remainingTime"
        }
    }
    fun createTimeLabel(time: Int): String {
        var timeLabel = ""
        var min = time / 1000 / 60
        var sec = time / 1000 % 60

        timeLabel = "$min:"
        if (sec < 10) timeLabel += "0"
        timeLabel += sec

        return timeLabel
    }
    override fun onAttach(context: Context) {
          super.onAttach(context)
          if (context is Activity){
              actividad = context
              interfaceMediaPlayer = actividad as InterfaceMediaPlayer
          }
    }
}

