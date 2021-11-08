package com.miso.vinilosapp.repository

import android.app.Application
import com.android.volley.VolleyError
import com.miso.vinilosapp.model.Album
import com.miso.vinilosapp.network.NetworkServiceAdapter


class AlbumRepository (val application: Application){
    fun refreshData(callback: (List<Album>)->Unit, onError: (VolleyError)->Unit) {
        //Determinar la fuente de datos que se va a utilizar. Si es necesario consultar la red, ejecutar el siguiente código
        NetworkServiceAdapter.getInstance(application).getAlbums({
            //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}