package com.example.stk47warehousejournalapp

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.stk47warehousejournalapp.data.db.UserLocalData
import com.example.stk47warehousejournalapp.data.db.UserLocalDataDao
import com.example.stk47warehousejournalapp.data.model.Event
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class LocalRoomDbTest {

    private lateinit var dao : UserLocalDataDao
    private lateinit var db : UserLocalData

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, UserLocalData::class.java).build()
        dao = db.userLocalDataDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeEventAndReadInList(){
        // Insert
        val mockEvent =
            Event( "1", "Pandora × STK47 Warehouse Party", "Techno/trance", "22/04", "Krakow", R.mipmap.event_image_1, false, false)
        dao.addLikedEvent(mockEvent)
        // Select
        val eventById = dao.getEventById(mockEvent.id)
        assertThat(eventById, equalTo(mockEvent))
    }

    @Test
    @Throws(Exception::class)
    fun deleteEventAndRead(){
        // Insert
        val mockEvent =
            Event( "1", "Pandora × STK47 Warehouse Party", "Techno/trance", "22/04", "Krakow", R.mipmap.event_image_1, false, false)
        dao.addLikedEvent(mockEvent)
        // Select
        val eventById = dao.getEventById(mockEvent.id)
        assertThat(eventById, equalTo(mockEvent))
        // Delete
        dao.deleteLikedEvent(mockEvent)
        val deletedEventById = dao.getEventById(mockEvent.id)
        Assert.assertNull(deletedEventById)
    }

}