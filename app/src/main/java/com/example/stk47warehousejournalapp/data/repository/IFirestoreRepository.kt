package com.example.stk47warehousejournalapp.data.repository

import androidx.lifecycle.LiveData
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.data.model.Result
import com.google.firebase.firestore.CollectionReference

interface IFirestoreRepository {
    fun getAllEvents() : LiveData<List<Event>>
    suspend fun getUpcomingEvents() : Result<Exception, List<Event>>
}