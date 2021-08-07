package com.wandal.wave.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.wandal.wave.data.WaveRepository

class ViewModelFactory(private val waveRepository: WaveRepository): ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ViewModel(waveRepository) as T
    }
}