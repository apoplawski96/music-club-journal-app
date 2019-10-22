package com.example.stk47warehousejournalapp.ui.screens.upcomingevents

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stk47warehousejournalapp.data.model.Artist
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.data.network.FakeDatabase
import com.example.stk47warehousejournalapp.data.repository.AppRepository
import com.example.stk47warehousejournalapp.data.repository.IFirestoreRepository
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.QuerySnapshot
import io.reactivex.Observable
import kotlinx.coroutines.launch

class UpcomingEventsViewModel(private val appRepository: AppRepository, private val firestoreRepository : IFirestoreRepository) : ViewModel() {
    private  val TAG = "UpcomingEventsViewModel"
    // Collections
    private var upcomingEvents : MutableLiveData<List<Event>> = MutableLiveData()
    private var artistsLineUpList : MutableLiveData<List<Artist>> = MutableLiveData()

    // Local db

    fun addLikedEvent(event : Event) = viewModelScope.launch {
        appRepository.addLikedEvent(event)
    }

    fun removeLikedEvent(event : Event) = viewModelScope.launch {
        appRepository.removeLikedEvent(event)
    }

    suspend fun getLikedEvents() : List<Event>{
        return appRepository.getLikedEvents()
    }

    fun getUserLikedEvents() : Observable<List<Event>> {
        return appRepository.getUserLikedEvents()
    }

    fun nukeLikedEventsTable() = viewModelScope.launch {
        appRepository.nukeLikedEventsTable()
    }

    // Firebase db

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
