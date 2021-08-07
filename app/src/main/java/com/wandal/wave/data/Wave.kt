package com.wandal.wave.data

data class Wave(val waveText : String, val author: String) {
    override fun toString(): String {
        return "$waveText - $author"
    }
}