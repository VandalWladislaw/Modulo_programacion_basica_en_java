package com.wandal.wave.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.wandal.wave.data.entities.TrackEntity


@Dao
interface WaveDao {
    //acciones en base de datos

    //funcion insertar trackEntities
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTracks(trackEntities: List<TrackEntity>)

    @Query("SELECT * FROM track_entity")
    suspend fun readTracks():List<TrackEntity>

}