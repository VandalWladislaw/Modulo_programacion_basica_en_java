package com.wandal.wave.data

class WaveDatabase private constructor(){

    var waveDao = WaveDao()
    private set
    companion object {
        @Volatile private var instance: WaveDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this){
                instance ?: WaveDatabase().also {instance = it}
            }
    }
}