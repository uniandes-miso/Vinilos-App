package com.miso.vinilosapp.models

data class Collector(
    val Id: Int,
    val name: String,
    val telephone: String,
    val email: String,
    val favoritePerformers: List<Musician>?,
    var collectorAlbums:List<Album>?
)
