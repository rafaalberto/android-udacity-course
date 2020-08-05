package br.com.marsestate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.marsestate.network.MarsApi
import br.com.marsestate.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class MarsApiStatus { LOADING, ERROR, DONE }

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<MarsApiStatus>()
    val status : LiveData<MarsApiStatus> get() = _status

    private val _properties = MutableLiveData<List<MarsProperty>>()
    val properties: LiveData<List<MarsProperty>> get() = _properties

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getMarsRealEstateProperties()
    }

    private fun getMarsRealEstateProperties() {
        coroutineScope.launch {
            val getPropertiesDeferred = MarsApi.retrofitService.getProperties()
            try {
                _status.value = MarsApiStatus.LOADING
                val result = getPropertiesDeferred.await()
                _status.value = MarsApiStatus.DONE
                _properties.value = result
            } catch (t: Throwable) {
                _status.value = MarsApiStatus.ERROR
                _properties.value = ArrayList()
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}