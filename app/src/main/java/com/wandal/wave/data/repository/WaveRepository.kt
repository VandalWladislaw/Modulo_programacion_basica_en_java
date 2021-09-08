package com.wandal.wave.data.repository

import android.util.Log
import com.wandal.wave.Track
import com.wandal.wave.data.WaveApp.Companion.db
import com.wandal.wave.data.entities.TrackEntity
import com.wandal.wave.data.repository.conversor.ConversorWave
import com.wandal.wave.utilities.TrackService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class WaveRepository () {

    private var trackService = TrackService()


    fun getTracks(): Flow<List<Track>> = flow{
        while(true){
            //obtener datos y guardar en response
            val trackResponse = kotlin.runCatching { trackService.getTracks() }
            trackResponse.onSuccess{
                //si se bajan los datos, mandar a bbdd

                if(it.body()!=null){
                    db.waveDao().insertTracks(ConversorWave.convertTracksEntity(it.body()!!))
                    //re100 insertamos en db
                }
            }
            trackResponse.onFailure{
                Log.d("FallaLecturaTracks",it.toString())
            }
            //obtener datos de la bd
            var tracksEntity: List<TrackEntity> = db.waveDao().readTracks()
            if(tracksEntity!=null){
                emit(ConversorWave.convertTracks(tracksEntity))
            }
            kotlinx.coroutines.delay(5000)
        }
    }.flowOn(Dispatchers.IO)

}