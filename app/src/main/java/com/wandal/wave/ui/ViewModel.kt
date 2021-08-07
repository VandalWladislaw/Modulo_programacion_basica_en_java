package com.wandal.wave.ui

import com.wandal.wave.data.WaveRepository
import com.wandal.wave.data.Wave
import androidx.lifecycle.ViewModel


class ViewModel(private val waveRepository: WaveRepository): ViewModel() {
    fun getWaves() = waveRepository.getWaves()

    fun addWave(wave: Wave) = waveRepository.addWave(wave)
}