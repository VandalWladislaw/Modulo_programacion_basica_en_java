package com.wandal.wave

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {
    private val api = Retrofit.Builder()
        .baseUrl("https://daniel.rayunmapu.cl/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    suspend fun getTracks(): Response<List<Track>> {
        return withContext(Dispatchers.IO){
            api.create(APIService::class.java).dataTrack()
        }

    }
}

