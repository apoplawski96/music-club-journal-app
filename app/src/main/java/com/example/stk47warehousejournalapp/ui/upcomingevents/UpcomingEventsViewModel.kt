package com.example.stk47warehousejournalapp.ui.upcomingevents

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.stk47warehousejournalapp.data.model.Artist
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.data.network.FakeDatabase
import com.example.stk47warehousejournalapp.data.network.FirestoreRepository
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot

class UpcomingEventsViewModel(private val firestoreRepository: FirestoreRepository, private val fakeDb : FakeDatabase) : ViewModel() {
    // Basic
    private  val TAG = "UpcomingEventsViewModel"
    // Collections
    private var upcomingEvents : MutableLiveData<List<Event>> = MutableLiveData()
    private var artistsLineUpList : MutableLiveData<List<Artist>> = MutableLiveData()

    fun getFakeEvents(): LiveData<List<Event>>{
        upcomingEvents.value = fakeDb.getUpcomingEvents()
        return upcomingEvents
    }

    fun getFakeArtist(): LiveData<List<Artist>>{
        artistsLineUpList.value = fakeDb.getArtistsList()
        return artistsLineUpList
    }

    fun getUpcomingEvents(): LiveData<List<Event>>{
        val upcomingEventsList : MutableList<Event> = mutableListOf()

        firestoreRepository.getAllEvents().addSnapshotListener(EventListener<QuerySnapshot> { value, e ->
            if (e != null) {
                Log.w(TAG, "Listen failed.", e)
                upcomingEvents.value = null
                return@EventListener
            }

            for (doc in value!!) {
                val eventItem = doc.toObject(Event::class.java)
                upcomingEventsList.add(eventItem)
            }
            upcomingEvents.value = upcomingEventsList
        })
        return upcomingEvents
    }
}
