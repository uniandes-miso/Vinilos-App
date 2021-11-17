package com.miso.vinilosapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.miso.vinilosapp.models.Collector
import com.miso.vinilosapp.network.NetworkServiceAdapter


class CollectorRepository (val application: Application){
    fun getMusicians(callback: (List<Collector>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getCollectors({
            callback(it)
        },
            onError
        )
    }

}