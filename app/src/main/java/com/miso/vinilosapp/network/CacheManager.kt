package com.miso.vinilosapp.network

import android.content.Context
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.models.Collector
import com.miso.vinilosapp.models.Musician

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
    private var collectors: HashMap<Int, List<Collector>> = hashMapOf()
    private var musicians: HashMap<Int, List<Musician>> = hashMapOf()

    fun addAlbums(albumId: Int, albumsList: List<Album>){
        if (!albums.containsKey(albumId)){
            albums[albumId] = albumsList
        }
    }

    fun getAlbums(albumId: Int): List<Album>{
        return if (albums.containsKey(albumId)) albums[albumId]!! else listOf<Album>()
    }

    fun addCollectors(collectorId: Int, collectorList: List<Collector>){
        if (!collectors.containsKey(collectorId)){
            collectors[collectorId] = collectorList
        }
    }

    fun getCollectors(collectorId: Int): List<Collector>{
        return if (collectors.containsKey(collectorId)) collectors[collectorId]!! else listOf<Collector>()
    }

    fun addMusicians(musicianId: Int, musicianList: List<Musician>){
        if (!musicians.containsKey(musicianId)){
            musicians[musicianId] = musicianList
        }
    }

    fun getMusicians(musicianId: Int): List<Musician>{
        return if (musicians.containsKey(musicianId)) musicians[musicianId]!! else listOf<Musician>()
    }
}