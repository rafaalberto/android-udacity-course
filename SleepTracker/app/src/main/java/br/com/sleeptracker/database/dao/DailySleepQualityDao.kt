package br.com.sleeptracker.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.sleeptracker.database.entity.DailySleepQuality

@Dao
interface DailySleepQualityDao {

    @Insert
    fun insert(dailySleep: DailySleepQuality)

    @Update
    fun update(dailySleep: DailySleepQuality)

    @Query("DELETE FROM daily_sleep_quality")
    fun deleteAll()

    @Query("SELECT * from daily_sleep_quality WHERE id = :id")
    fun getById(id: Long): DailySleepQuality?

    @Query("SELECT * from daily_sleep_quality WHERE id = :id")
    fun findById(id: Long): LiveData<DailySleepQuality>

    @Query("SELECT * FROM daily_sleep_quality ORDER BY id DESC")
    fun findAll(): LiveData<List<DailySleepQuality>>

    @Query("SELECT * FROM daily_sleep_quality ORDER BY id DESC LIMIT 1")
    fun findTonight(): DailySleepQuality?

}