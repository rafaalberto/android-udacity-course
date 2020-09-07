package br.com.devbytes.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.devbytes.domain.Video
import br.com.devbytes.network.Network
import br.com.devbytes.network.asDomainModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import java.io.IOException

class DevByteViewModel(application: Application) : AndroidViewModel(application) {

    private val viewModelJob = SupervisorJob()

    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _playlist = MutableLiveData<List<Video>>()

    val playlist: LiveData<List<Video>> get() = _playlist

    init {
        refreshDataFromNetwork()
    }

    private fun refreshDataFromNetwork() = viewModelScope.launch {
        try {
            val playlist = Network.devBytes.getPlaylist().await()
            _playlist.postValue(playlist.asDomainModel())
        } catch (netWorkError: IOException) {

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}