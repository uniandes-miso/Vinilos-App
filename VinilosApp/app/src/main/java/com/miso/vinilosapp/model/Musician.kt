package com.miso.vinilosapp.model

data class Musician(
    val Id: Int,
    val name: String,
    val image: String,
    val description: String,
    val birthDate: String,
    var albums:List<Album>?
)
