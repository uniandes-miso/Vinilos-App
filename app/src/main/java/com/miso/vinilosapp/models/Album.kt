package com.miso.vinilosapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
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
) : Parcelable
