package com.wandal.wave

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer

interface interfaceMediaPlayer {

    fun getmp(): MediaPlayer
    fun getdt(): MediaMetadataRetriever
    //fun getdtimg():

}