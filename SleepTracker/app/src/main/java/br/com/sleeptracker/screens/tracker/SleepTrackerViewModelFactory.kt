package br.com.sleeptracker.screens.tracker

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.sleeptracker.database.dao.DailySleepQualityDao
import java.lang.IllegalArgumentException

@Suppress("UNCHECKED_CAST")
class SleepTrackerViewModelFactory(
    private val dataSource: DailySleepQualityDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepTrackerViewModel::class.java))
            return SleepTrackerViewModel(dataSource, application) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}