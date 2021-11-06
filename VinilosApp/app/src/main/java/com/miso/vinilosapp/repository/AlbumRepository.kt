package com.miso.vinilosapp.repository

import android.app.Application
import com.android.volley.VolleyError
import com.miso.vinilosapp.model.Album
import com.miso.vinilosapp.repository.NetworkServiceAdapter


class AlbumRepository (val application: Application){
    fun getAlbums(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        NetworkServiceAdapter.getInstance(application).getAlbums({
            callback(it)
        },
            onError
        )
    }

}