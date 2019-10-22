package com.example.stk47warehousejournalapp.data.repository

import com.example.stk47warehousejournalapp.internal.*
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.data.model.Result
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

const val EVENTS = "events"

class IFirestoreRepositoryImpl(
    val db : FirebaseFirestore = FirebaseFirestore.getInstance()
) : IFirestoreRepository {

    override fun getAllEvents() : CollectionReference {
        return db.collection(EVENTS)
    }

    override suspend fun getUpcomingEvents(): Result<Exception, List<Event>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}