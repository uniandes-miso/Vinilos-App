package com.miso.vinilosapp.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.miso.vinilosapp.models.Album
import com.miso.vinilosapp.models.Musician
import com.miso.vinilosapp.repositories.AlbumRepository
import com.miso.vinilosapp.repositories.MusicianRepository

class MusicianViewModel(application: Application) :  AndroidViewModel(application) {

        private val musiciansRepository = MusicianRepository(application)

        private val _musicians = MutableLiveData<List<Musician>>()

        val musicians: LiveData<List<Musician>>
            get() = _musicians

        private var _eventNetworkError = MutableLiveData<Boolean>(false)

        val eventNetworkError: LiveData<Boolean>
            get() = _eventNetworkError

        private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

        val isNetworkErrorShown: LiveData<Boolean>
            get() = _isNetworkErrorShown

        init {
            Log.d("REFRESH DATA MUSICIANS" , "REFRESH DATA MUSICIANS")
            refreshMusiciansDataFromNetwork()
        }

        private fun refreshMusiciansDataFromNetwork() {
            musiciansRepository.getMusicians({
                _musicians.postValue(it)
                _eventNetworkError.value = false
                _isNetworkErrorShown.value = false
            },{
                _eventNetworkError.value = true
            })
        }

        fun onNetworkErrorShown() {
            _isNetworkErrorShown.value = true
        }

        class Factory(val app: Application) : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if (modelClass.isAssignableFrom(MusicianViewModel::class.java)) {
                    @Suppress("UNCHECKED_CAST")
                    return MusicianViewModel(app) as T
                }
                throw IllegalArgumentException("Unable to construct viewmodel")
            }
        }
    }
