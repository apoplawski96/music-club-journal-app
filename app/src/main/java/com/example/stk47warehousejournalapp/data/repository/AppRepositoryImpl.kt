package com.example.stk47warehousejournalapp.data.repository

import androidx.lifecycle.LiveData
import com.example.stk47warehousejournalapp.data.db.UserLocalDataDao
import com.example.stk47warehousejournalapp.data.model.Event
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import io.reactivex.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AppRepositoryImpl (private val userLocalDataDao : UserLocalDataDao) : AppRepository {

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

    override suspend fun getLikedEvents(): List<Event> {
        return userLocalDataDao.getLikedEvents()
    }

    override suspend fun addLikedEvent(event : Event){
        userLocalDataDao.addLikedEvent(event)
    }

    override fun getUserLikedEvents(): Observable<List<Event>> {
        return userLocalDataDao.getLikedEventsInObservable()
    }

    override suspend fun nukeLikedEventsTable() {
        userLocalDataDao.nukeLikedEventsTable()
    }

    override suspend fun removeLikedEvent(event: Event) {
        userLocalDataDao.deleteLikedEvent()
    }

}