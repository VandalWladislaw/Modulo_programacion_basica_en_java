package com.wandal.wave.utilities

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    fun getRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://daniel.rayunmapu.cl/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}