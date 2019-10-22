package com.example.stk47warehousejournalapp.data.repository

import androidx.lifecycle.LiveData
import com.example.stk47warehousejournalapp.data.model.Event
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import io.reactivex.Observable

interface AppRepository {

    // Firebase

    fun getUpcomingEvents() : Query

    fun getAllEvents() : CollectionReference

    // Room

    fun getUserLikedEvents() : Observable<List<Event>>

    // Artifacts?

    suspend fun getLikedEvents() : List<Event>
    suspend fun addLikedEvent(event : Event)
    suspend fun nukeLikedEventsTable()
    suspend fun removeLikedEvent(event : Event)
}