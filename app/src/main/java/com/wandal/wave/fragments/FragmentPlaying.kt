package com.wandal.wave.fragments

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.SeekBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.wandal.wave.R
import com.wandal.wave.interfaceMediaPlayer
import kotlinx.android.synthetic.main.fragment_playing.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentPlaying.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentPlaying : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var totalTime: Int = 0
    private var artist: String? = null
    private var album: String? = null
    private var title: String? = null

    lateinit var btnPlay: Button
    //lateinit var btnStop: Button
    lateinit var sbar: SeekBar
    lateinit var vista: View
    lateinit var actividad:Activity
    lateinit var interfaceMediaPlayer: interfaceMediaPlayer



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        interfaceMediaPlayer.getmp().isLooping = false
        totalTime = interfaceMediaPlayer.getmp().duration

        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)

        }
    }

    fun play(){
        if(interfaceMediaPlayer.getmp().isPlaying){
            //pausa
            interfaceMediaPlayer.getmp().pause()
            //buttonPlayback.setBackgroundResource(R.drawable.ic_play) //esto igual sirve
            btnPlay.setBackgroundResource(R.drawable.ic_play)
        } else {
            //start
            interfaceMediaPlayer.getmp().start()
            //buttonPlayback.setBackgroundResource(R.drawable.ic_pause) //esto igual sirve
            btnPlay.setBackgroundResource(R.drawable.ic_pause)

            }
    }
    /*fun stop(){
        if(interfaceMediaPlayer.getmp().isPlaying){
            interfaceMediaPlayer.getmp().stop()
            interfaceMediaPlayer.getmp().release()
            btnPlay.setBackgroundResource(R.drawable.ic_play)
        }
    }*/

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        vista = inflater.inflate(R.layout.fragment_playing, container, false)

        //Metadata

        /*
        try {

            MediaMetadataRetriever mmr = new MediaMetadataRetriever()
            mmr.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
            albumName =
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            title =
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
            artist =
                mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("MediaMetadataRetriever","title " + title + "  albumName " + albumName+ " artist " + artist );
        */








        //boton play
        btnPlay = vista.findViewById<Button>(R.id.buttonPlayback)
        btnPlay.setOnClickListener(object: View.OnClickListener {
            override fun onClick(view: View?)
            {
                play()
            }
        })

        //seekbar
        sbar = vista.findViewById<SeekBar>(R.id.positionBar)
        sbar.max = totalTime
        sbar.setOnSeekBarChangeListener(
            object : SeekBar.OnSeekBarChangeListener{
                override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean){
                    if(fromUser){
                        interfaceMediaPlayer.getmp().seekTo(progress)
                        sbar.progress = progress
                    }
                }

                override fun onStartTrackingTouch(seekBar: SeekBar?) {

                }

                override fun onStopTrackingTouch(seekBar: SeekBar?) {

                }

            }
        )
        //Thread
        Thread(Runnable{
            while(interfaceMediaPlayer.getmp() != null){
                try{
                    var msg = Message()
                    msg.what = interfaceMediaPlayer.getmp().currentPosition
                    handler.sendMessage(msg)
                    Thread.sleep(1000)

                } catch (e: InterruptedException){

                }
            }
        }).start()

        //caratula

        //boton stop
        /*btnStop = vista.findViewById<Button>(R.id.buttonStop)
        btnStop.setOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?){
                stop()
            }
        })*/

        return vista
    }
    @SuppressLint("HandlerLeak")
    var handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            val currentPosition = msg.what

            // Update positionBar
            positionBar.progress = currentPosition

            // Update Labels
            val elapsedTime = createTimeLabel(currentPosition)
            textElapsed.text = elapsedTime

            val remainingTime = createTimeLabel(totalTime - currentPosition)
            textRemaining.text = "-$remainingTime"
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

    private fun dialogo(titulo:String, mensaje:String)
    {
        val builder = AlertDialog.Builder(requireActivity())
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is Activity){
            actividad = context
            interfaceMediaPlayer = actividad as interfaceMediaPlayer

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentPlaying.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentPlaying().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

