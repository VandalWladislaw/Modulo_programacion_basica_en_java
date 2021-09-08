package com.wandal.wave.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.wandal.wave.RetrofitService
import com.wandal.wave.Track
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import com.wandal.wave.data.repository.WaveRepository

class ViewModelTrack(/*private val waveRepository: WaveRepository*/): ViewModel() {
    private val repositorio = WaveRepository()
    var tracks = repositorio.getTracks().asLiveData()
}