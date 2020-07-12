package br.com.sleeptracker.screens.tracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import br.com.sleeptracker.database.dao.DailySleepQualityDao

class SleepTrackerViewModel(
    val dailySleepQualityDao: DailySleepQualityDao,
    application: Application) : AndroidViewModel(application) {



}