package com.example.stk47warehousejournalapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.stk47warehousejournalapp.data.model.Event
import io.reactivex.Observable

@Dao
interface UserLocalDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addLikedEvent(event : Event)

    @Query("SELECT * FROM liked_event")
    fun getUserLikedEventsAsLiveData() : LiveData<List<Event>>

    @Query("SELECT * FROM liked_event")
    fun getUserLikedEventsAsObservable() : Observable<List<Event>>

    @Query("SELECT * FROM liked_event WHERE id LIKE :id")
    fun getEventById(id : String) : Event

    @Delete
    fun deleteLikedEvent(event: Event)

    @Query("DELETE FROM liked_event")
    suspend fun nukeLikedEventsTable()

}