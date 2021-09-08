package com.wandal.wave.interfaces

import android.media.MediaMetadataRetriever
import android.media.MediaPlayer
import android.net.Uri
import android.os.Handler

interface InterfaceMediaPlayer {

    fun getmp(): MediaPlayer
    fun getdt(): MediaMetadataRetriever
    fun geturi(): Uri
    fun mHandler(): Handler

}