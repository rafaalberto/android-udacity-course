package br.com.sleeptracker.screens.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.sleeptracker.database.dao.DailySleepQualityDao

@Suppress("UNCHECKED_CAST")
class SleepDetailViewModelFactory(
    private val sleepNightKey: Long,
    private val dataSource: DailySleepQualityDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SleepDetailViewModel::class.java))
            return SleepDetailViewModel(sleepNightKey, dataSource) as T
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}