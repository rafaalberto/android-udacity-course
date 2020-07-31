package br.com.sleeptracker.screens.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.sleeptracker.database.dao.DailySleepQualityDao
import br.com.sleeptracker.database.entity.DailySleepQuality
import kotlinx.coroutines.Job

class SleepDetailViewModel(
    private val sleepNightKey: Long = 0L,
    dataSource: DailySleepQualityDao
) : ViewModel() {

    val database = dataSource

    private val viewModelJob = Job()

    private val night = MediatorLiveData<DailySleepQuality>()

    fun getNight() = night

    init {
        night.addSource(database.findById(sleepNightKey), night::setValue)
    }

    private val _navigateToSleepTracker = MutableLiveData<Boolean?>()
    val navigateToSleepTracker: LiveData<Boolean?> get() = _navigateToSleepTracker

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    fun doneNavigation() {
        _navigateToSleepTracker.value = null
    }

    fun onClose() {
        _navigateToSleepTracker.value = true
    }

}