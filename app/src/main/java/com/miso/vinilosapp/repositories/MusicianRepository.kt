package com.miso.vinilosapp.repositories

import android.app.Application
import android.util.Log
import com.android.volley.VolleyError
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.models.Musician
import com.miso.vinilosapp.network.NetworkServiceAdapter

class MusicianRepository(val application: Application) {
    fun refreshData(callback: (List<Musician>)->Unit, onError: (VolleyError)->Unit) {
        Log.d("INGRESO A REFRESH DATA", "REFRESH DATA OK ")
        NetworkServiceAdapter.getInstance(application).getMusicians({
            //Guardar los albumes de la variable it en un almacén de datos local para uso futuro
            callback(it)
        },
            onError
        )
    }
}