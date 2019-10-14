package com.example.stk47warehousejournalapp.data.network

import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query

interface FirestoreRepository {
    fun getUpcomingEvents() : Query
    fun getAllEvents() : CollectionReference
}