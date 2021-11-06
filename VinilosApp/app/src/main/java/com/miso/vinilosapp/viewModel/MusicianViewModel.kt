package com.miso.vinilosapp.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.miso.vinilosapp.model.Album
import com.miso.vinilosapp.model.Musician

class MusicianViewModel : ViewModel(){
    private val musicians: MutableLiveData<List<Musician>> by lazy {
        MutableLiveData<List<Musician>>().also {
            loadMusicians()
        }
    }

    fun getMusicians(): LiveData<List<Musician>> {
        return musicians
    }

    private fun loadMusicians() {
        // Do an asynchronous operation to fetch users.
    }
}