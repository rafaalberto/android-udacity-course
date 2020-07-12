package br.com.sleeptracker.database

import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.sleeptracker.database.dao.DailySleepQualityDao
import br.com.sleeptracker.database.entity.DailySleepQuality
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class SleepDatabaseTest {

    private lateinit var dailySleepQualityDao: DailySleepQualityDao
    private lateinit var sleepDatabase: SleepDatabase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        sleepDatabase = Room.inMemoryDatabaseBuilder(context, SleepDatabase::class.java).allowMainThreadQueries().build()
        dailySleepQualityDao = sleepDatabase.dailySleepQualityDao
    }

    @Test
    @Throws(Exception::class)
    fun insertAndFindTonight() {
        val dailySleep = DailySleepQuality()
        dailySleepQualityDao.insert(dailySleep)
        val tonight = dailySleepQualityDao.findTonight()
        Assert.assertEquals(-1, tonight?.qualityRating)
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        sleepDatabase.close()
    }
}
