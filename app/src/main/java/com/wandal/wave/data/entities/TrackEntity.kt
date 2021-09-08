package com.wandal.wave.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track_entity")

data class TrackEntity(
    @PrimaryKey @ColumnInfo(name = "pk") val pk: String,
    @ColumnInfo(name = "path") val path: String,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "startLoop") val startLoop: String,
    @ColumnInfo(name = "endLoop") val endLoop: String,
    @ColumnInfo(name = "speed") val speed: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "trackNumber") val trackNumber: String
)