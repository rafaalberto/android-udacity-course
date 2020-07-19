package br.com.sleeptracker.screens.tracker

import android.app.Application
import android.text.Spanned
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import br.com.sleeptracker.database.dao.DailySleepQualityDao
import br.com.sleeptracker.database.entity.DailySleepQuality
import br.com.sleeptracker.formatNights
import kotlinx.coroutines.*

class SleepTrackerViewModel(
    private val dailySleepQualityDao: DailySleepQualityDao,
    application: Application
) : AndroidViewModel(application) {

    private var viewModelJob = Job()

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var tonight = MutableLiveData<DailySleepQuality?>()

    private val nights = dailySleepQualityDao.findAll()

    val nightsString: LiveData<Spanned> =
        Transformations.map(nights) { nights -> formatNights(nights, application.resources) }

    init {
        initializeTonight()
    }

    private fun initializeTonight() {
        uiScope.launch {
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun getTonightFromDatabase(): DailySleepQuality? {
        return withContext(Dispatchers.IO) {
            var night = dailySleepQualityDao.findTonight()
            if (night?.endTimeMilli != night?.startTimeMilli) {
                night = null
            }
            night
        }
    }

    fun onStartTracking() {
        uiScope.launch {
            insert(DailySleepQuality())
            tonight.value = getTonightFromDatabase()
        }
    }

    private suspend fun insert(night: DailySleepQuality) {
        withContext(Dispatchers.IO) {
            dailySleepQualityDao.insert(night)
        }
    }

    fun onStopTracking() {
        uiScope.launch {
            val oldNight = tonight.value ?: return@launch
            oldNight.endTimeMilli = System.currentTimeMillis()
            update(oldNight)
        }
    }

    private suspend fun update(oldNight: DailySleepQuality) {
        withContext(Dispatchers.IO) {
            dailySleepQualityDao.update(oldNight)
        }
    }

    fun onClear() {
        uiScope.launch {
            clear()
            tonight.value = null
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            dailySleepQualityDao.deleteAll()
        }
    }

}