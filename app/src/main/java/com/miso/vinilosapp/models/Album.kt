package com.miso.vinilosapp.models

data class Album(
    val albumId:Int,
    val name:String,
    val cover:String,
    val releaseDate:String,
    val description:String,
    val genre:String,
    val recordLabel:String,
    var tracks:List<Track>?
    //var performers:List<Perfomer>?,
    //var comments:List<Comment>?
)
