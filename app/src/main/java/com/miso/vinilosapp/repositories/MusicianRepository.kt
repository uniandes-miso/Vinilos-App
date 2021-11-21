package com.miso.vinilosapp.repositories

import android.app.Application
import android.util.Log
import com.miso.vinilosapp.models.Musician
import com.miso.vinilosapp.network.CacheManager
import com.miso.vinilosapp.network.NetworkServiceAdapter

class MusicianRepository (val application: Application){
    private var id_musician: Int = 997
    suspend fun getMusicians(): List<Musician> {
        var memResponse = CacheManager.getInstance(application.applicationContext).getMusicians(id_musician)
        if(memResponse.isEmpty()){
            var listMusicians = NetworkServiceAdapter.getInstance(application).getMusicians()
            CacheManager.getInstance(application.applicationContext).addMusicians(id_musician,listMusicians)
            Log.d("Musicians", "FROM RED")
            return listMusicians
        }else{
            Log.d("Musicians", "FROM CACHE")
            return memResponse
        }
    }

}