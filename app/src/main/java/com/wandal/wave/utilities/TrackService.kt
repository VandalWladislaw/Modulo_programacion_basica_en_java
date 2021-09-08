package com.wandal.wave.utilities

import com.wandal.wave.APIService
import com.wandal.wave.Track
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class TrackService {

    //obtener retrofit de objeto
    private val retrofit = RetrofitHelper.getRetrofit()
    //bajar conversion
    suspend fun getTracks(): Response<List<Track>> {
        return withContext(Dispatchers.IO){
            retrofit.create(APIService::class.java).dataTrack()
        }
    }

}