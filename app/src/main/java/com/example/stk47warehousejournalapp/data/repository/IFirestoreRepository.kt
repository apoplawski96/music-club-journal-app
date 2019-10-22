package com.example.stk47warehousejournalapp.data.repository

import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.data.model.Result

interface IFirestoreRepository {
    suspend fun getAllEvents() : Result<Exception, List<Event>>
    suspend fun getUpcomingEvents() : Result<Exception, List<Event>>
}