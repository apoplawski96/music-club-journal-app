package com.example.stk47warehousejournalapp.data.repository

import androidx.lifecycle.LiveData
import com.example.stk47warehousejournalapp.data.model.Event
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query
import io.reactivex.Observable

interface AppRepository {

    fun getUserLikedEventsAsLiveData() : LiveData<List<Event>>

    fun getUserLikedEventsAsObservable() : Observable<List<Event>>

    fun addLikedEvent(event : Event)

    fun deleteLikedEvent(event : Event)

}