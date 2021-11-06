package com.miso.vinilosapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miso.vinilosapp.model.Album

class AlbumViewModel : ViewModel(){
    private val albums: MutableLiveData<List<Album>> by lazy {
        MutableLiveData<List<Album>>().also {
            loadAlbums()
        }
    }

    fun getAlbums(): LiveData<List<Album>> {
        return albums
    }

    private fun loadAlbums() {
        // Do an asynchronous operation to fetch users.
    }
}


