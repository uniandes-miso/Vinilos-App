package com.miso.vinilosapp.repositories

import android.app.Application
import com.android.volley.VolleyError
import com.miso.vinilosapp.models.Musician
import com.miso.vinilosapp.network.NetworkServiceAdapter

class MusicianRepository (val application: Application){
    fun getMusicians(callback: (List<Musician>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getMusicians({
            callback(it)
        },
            onError
        )
    }

}