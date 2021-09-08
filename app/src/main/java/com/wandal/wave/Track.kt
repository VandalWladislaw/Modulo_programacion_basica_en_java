package com.wandal.wave

data class Track(
    val pk: String,
    val path: String,
    val title: String,
    val startLoop: String,
    val endLoop: String,
    val speed: String,
    val description: String,
    val trackNumber: String)