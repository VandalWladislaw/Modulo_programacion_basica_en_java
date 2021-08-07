package com.wandal.wave.utilities

import com.wandal.wave.data.WaveDatabase
import com.wandal.wave.data.WaveRepository
import com.wandal.wave.ui.ViewModelFactory

object InjectorUtils {

    fun provideViewModelFactory(): ViewModelFactory {
        val waveRepository = WaveRepository.getInstance(WaveDatabase.getInstance().waveDao)
        return ViewModelFactory(waveRepository)
    }

}