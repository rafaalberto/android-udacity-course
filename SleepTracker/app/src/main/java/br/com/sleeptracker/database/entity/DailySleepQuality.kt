package br.com.sleeptracker.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_sleep_quality")
data class DailySleepQuality(

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,

    @ColumnInfo(name = "start_time_milli")
    val startTimeMilli: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "end_time_milli")
    var endTimeMilli: Long = startTimeMilli,

    @ColumnInfo(name = "quality_rating")
    var qualityRating: Int = NO_SELECTED_VALUE
) {
    companion object {
        const val NO_SELECTED_VALUE = -1
    }
}
