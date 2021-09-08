package com.wandal.wave.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wandal.wave.data.entities.TrackEntity

@Database(entities = [TrackEntity::class], version = 1, exportSchema = false)
abstract class WaveDatabase: RoomDatabase(){
    //tomar todas las consultas de WaveDao
    abstract fun waveDao(): WaveDao
    //colocar todos los dao en esta clase

}