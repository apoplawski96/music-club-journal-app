package com.example.stk47warehousejournalapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stk47warehousejournalapp.data.model.Event
import io.reactivex.Observable

@Dao
interface UserLocalDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLikedEvent(event : Event)

    @Query("SELECT * FROM liked_event")
    fun getUserLikedEvents() : List<Event>

    @Query("SELECT * FROM liked_event")
    fun getUserLikedEventsAsObservable() : Observable<List<Event>>

    @Query("DELETE FROM liked_event")
    suspend fun nukeLikedEventsTable()

}