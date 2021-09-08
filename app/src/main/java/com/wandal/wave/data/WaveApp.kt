package com.wandal.wave.data

import android.app.Application
import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase

class WaveApp:Application() {
    companion object {
        lateinit var db:WaveDatabase
    }
    override fun onCreate(){
        super.onCreate()
        db = Room.databaseBuilder(this, WaveDatabase::class.java, "Wave_database").build()//contexto, calse, nombre db
    }
}