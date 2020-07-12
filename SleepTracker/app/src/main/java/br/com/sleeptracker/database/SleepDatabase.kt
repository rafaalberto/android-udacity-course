package br.com.sleeptracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.sleeptracker.database.dao.DailySleepQualityDao
import br.com.sleeptracker.database.entity.DailySleepQuality

@Database(entities = [DailySleepQuality::class], version = 1, exportSchema = false)
abstract class SleepDatabase : RoomDatabase() {

    abstract val dailySleepQualityDao: DailySleepQualityDao

    companion object {

        @Volatile
        private var INSTANCE: SleepDatabase? = null

        fun getInstance(context: Context): SleepDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SleepDatabase::class.java,
                        "sleep_history_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }

}