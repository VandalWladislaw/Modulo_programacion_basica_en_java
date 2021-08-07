package com.wandal.wave.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class WaveDao {
    private val waveList = mutableListOf<Wave>()
    private val waves = MutableLiveData<List<Wave>>()

    init {
        waves.value = waveList
    }
    fun addWave(wave: Wave){
        waveList.add(wave)
        waves.value = waveList
    }

    fun getWaves() = waves as LiveData<List<Wave>>

}