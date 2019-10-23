package com.example.stk47warehousejournalapp.ui.screens.upcomingevents

import android.util.Log
import androidx.lifecycle.*
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

    val TAG = "UpcomingEventsViewModel"

    private var upcomingEventsWithUserData = MediatorLiveData<List<Event>>()

    fun observeOnData(owner : LifecycleOwner, observer : Observer<List<Event>>) = viewModelScope.launch{

        upcomingEventsWithUserData.observe(owner, observer)

        // Fetching data from Firebase db
        upcomingEventsWithUserData.addSource(firestoreRepository.getAllEvents()) { fetchedEvents ->
            if (fetchedEvents != null){
                upcomingEventsWithUserData.value = fetchedEvents
            }
        }

        // Fetching data from local Room db
        lazy {
            upcomingEventsWithUserData.addSource(appRepository.getUserLikedEventsAsLiveData()) { userLikedEvents ->
                if (userLikedEvents != null){
                    val remoteFetchedEvents = upcomingEventsWithUserData.value
                }
            }
        }
    }

    fun addLikedEvent(event: Event){
        appRepository.addLikedEvent(event)
    }

    fun fetchIsLikedValueToRemoteEvents(){

    }

}
