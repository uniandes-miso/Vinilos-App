package com.miso.vinilosapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Collector(
    val collectorId: Int,
    val name: String,
    val telephone: String,
    val email: String  ,
    val comments: List<Comment>?
    //val favoritePerformers: List<Musician>?,
    //var collectorAlbums:List<Album>?
): Parcelable
