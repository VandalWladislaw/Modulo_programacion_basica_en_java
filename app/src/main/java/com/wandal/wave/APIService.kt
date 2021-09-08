package com.wandal.wave

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface APIService {
    @GET("audiospur.json")
    suspend fun dataTrack(): Response<List<Track>>
}