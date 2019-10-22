package com.example.stk47warehousejournalapp.data.repository

import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.data.model.Result
import com.google.firebase.firestore.CollectionReference

interface IFirestoreRepository {
    fun getAllEvents() : CollectionReference
    suspend fun getUpcomingEvents() : Result<Exception, List<Event>>
}