package com.example.stk47warehousejournalapp.data.network

import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

const val EVENTS = "events"

class FirestoreRepositoryImpl : FirestoreRepository {

    val TAG = "FIREBASE_REPOSITORY"
    val db = FirebaseFirestore.getInstance()

    override fun getUpcomingEvents() : Query {
        var ref = db.collection(EVENTS).whereEqualTo("asd", "asd")
        return ref
    }

    override fun getAllEvents() : CollectionReference {
        var ref = db.collection(EVENTS)
        return ref
    }
}