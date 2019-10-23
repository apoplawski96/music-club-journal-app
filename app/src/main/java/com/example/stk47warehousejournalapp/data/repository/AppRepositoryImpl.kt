package com.example.stk47warehousejournalapp.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    override fun getUserLikedEventsAsObservable(): Observable<List<Event>> {
        return userLocalDataDao.getUserLikedEventsAsObservable()
    }

    override fun getUserLikedEventsAsLiveData(): LiveData<List<Event>> {
        return userLocalDataDao.getUserLikedEventsAsLiveData()
    }

    override fun addLikedEvent(event: Event) {
        GlobalScope.launch(Dispatchers.IO) {
            userLocalDataDao.addLikedEvent(event)
        }
    }
}