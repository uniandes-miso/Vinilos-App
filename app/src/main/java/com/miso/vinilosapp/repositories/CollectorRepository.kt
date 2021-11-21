package com.miso.vinilosapp.repositories

import android.app.Application
import android.util.Log
import com.miso.vinilosapp.models.Collector
import com.miso.vinilosapp.network.CacheManager
import com.miso.vinilosapp.network.NetworkServiceAdapter


class CollectorRepository (val application: Application){
    private var id_collect: Int = 998
   suspend fun getCollectors(): List<Collector> {
       var memResponse = CacheManager.getInstance(application.applicationContext).getCollectors(id_collect)
       if(memResponse.isEmpty()){
           var listCollectors = NetworkServiceAdapter.getInstance(application).getCollectors()
           CacheManager.getInstance(application.applicationContext).addCollectors(id_collect,listCollectors)
           Log.d("Collectors", "FROM RED")
           return listCollectors
       }else{
           Log.d("Collectors", "FROM CACHE")
           return memResponse
       }
   }

}