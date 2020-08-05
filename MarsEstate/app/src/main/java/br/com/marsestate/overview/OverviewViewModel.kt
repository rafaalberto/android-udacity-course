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

class OverviewViewModel : ViewModel() {

    private val _status = MutableLiveData<String>()
    val status: LiveData<String> get() = _status

    private val _property = MutableLiveData<MarsProperty>()
    val property: LiveData<MarsProperty> get() = _property

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
                if(result.isNotEmpty()) {
                    _property.value = result[0]
                }
            } catch (t: Throwable) {
                _status.value = "Failure: ${t.message}"
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}