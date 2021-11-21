package com.miso.vinilosapp.network

import android.content.Context
import com.miso.vinilosapp.models.Album

class CacheManager(context:Context) {
    companion object{
        var instance: CacheManager? = null
        fun getInstance(context: Context) =
            instance ?: synchronized(this) {
                instance ?: CacheManager(context).also {
                    instance = it
                }
            }
    }

    private var albums: HashMap<Int, List<Album>> = hashMapOf()
    fun addAlbums(albumId: Int, comment: List<Album>){
        if (!albums.containsKey(albumId)){
            albums[albumId] = comment
        }
    }

    fun getAlbums(albumId: Int): List<Album>{
        return if (albums.containsKey(albumId)) albums[albumId]!! else listOf<Album>()
    }
}