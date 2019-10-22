package com.example.stk47warehousejournalapp.data.repository

import com.example.stk47warehousejournalapp.internal.*
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.data.model.Result
import com.google.firebase.firestore.FirebaseFirestore

class IFirestoreRepositoryImpl(
    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
) : IFirestoreRepository {

    override suspend fun getAllEvents(): Result<Exception, List<Event>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getUpcomingEvents(): Result<Exception, List<Event>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}