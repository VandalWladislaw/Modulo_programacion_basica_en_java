package com.wandal.wave.data

class WaveRepository private constructor(private val waveDao: WaveDao) {

    fun addWave(wave: Wave){
        waveDao.addWave(wave)
    }

    fun getWaves() = waveDao.getWaves()

    companion object {
        @Volatile private var instance: WaveRepository? = null

        fun getInstance(waveDao: WaveDao): WaveRepository =
            instance ?: synchronized(this){
                instance ?: WaveRepository(waveDao).also {instance = it}
            }
    }
}