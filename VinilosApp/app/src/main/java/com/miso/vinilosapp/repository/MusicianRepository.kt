package com.miso.vinilosapp.repository

import android.app.Application
import com.android.volley.VolleyError
import com.miso.vinilosapp.model.Album
import com.miso.vinilosapp.model.Musician

class MusicianRepository (val application: Application){
    fun getMusicians(callback: (List<Musician>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getMusicians({
            callback(it)
        },
            onError
        )
    }

}