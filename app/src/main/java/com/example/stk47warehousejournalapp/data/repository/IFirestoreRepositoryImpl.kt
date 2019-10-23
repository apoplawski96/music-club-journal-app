package com.example.stk47warehousejournalapp.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.stk47warehousejournalapp.internal.*
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.data.model.Result
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

const val EVENTS = "events"
const val TAG = "HUI"

class IFirestoreRepositoryImpl(
    private val db : FirebaseFirestore = FirebaseFirestore.getInstance()
) : IFirestoreRepository {

    override fun getAllEvents() : LiveData<List<Event>> {
        val upcomingEventsList = MutableLiveData<List<Event>>()

        db.collection(EVENTS).addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                upcomingEventsList.value = null
                return@EventListener
            } else{
                val items = value?.toObjects(Event::class.java) ?: emptyList()
                upcomingEventsList.postValue(items)
            }
        })
        return upcomingEventsList
    }

    override suspend fun getUpcomingEvents(): Result<Exception, List<Event>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}