package com.miso.vinilosapp.repositories

import android.app.Application
import android.util.Log
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.network.CacheManager
import com.miso.vinilosapp.network.NetworkServiceAdapter

class AlbumRepository (val application: Application){
    private var id_Album: Int = 999
    suspend fun refreshData(): List<Album>{
        var memResponse = CacheManager.getInstance(application.applicationContext).getAlbums(id_Album)
        if(memResponse.isEmpty()){
            var listAlbums = NetworkServiceAdapter.getInstance(application).getAlbums()
            CacheManager.getInstance(application.applicationContext).addAlbums(id_Album,listAlbums)
            Log.d("Albums", "FROM RED")
            return listAlbums
        }else{
            Log.d("Albums", "FROM CACHE")
            return memResponse
        }
    }
}