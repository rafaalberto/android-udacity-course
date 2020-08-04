package br.com.marsestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.marsestate.network.MarsApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel : ViewModel() {

    private val _response = MutableLiveData<String>()
    val response: LiveData<String> get() = _response

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        coroutineScope.launch {
            val getPropertiesDeferred = MarsApi.retrofitService.getProperties()
            try {
                val result = getPropertiesDeferred.await()
                _response.value = "Success: ${result.size} Mars properties retrieved"
            } catch (t: Throwable) {
                _response.value = "Failure: ${t.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}