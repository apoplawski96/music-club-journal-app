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

    private var upcomingEventsOutputData = MediatorLiveData<List<Event>>()
    var remoteFetchedEventsTempList = MutableLiveData<List<Event>>()
    var localUserLikedEventsTempList = MutableLiveData<List<Event>>()

    fun addLikedEvent(event : Event){
        Log.d(TAG, "Event added to local db")
        appRepository.addLikedEvent(event)
    }

    fun removeLikedEvent(event : Event){
        Log.d(TAG, "Event deleted from local db")
        appRepository.deleteLikedEvent(event)
    }

    fun fetchUpcomingEvents() : LiveData<List<Event>>{
        return  firestoreRepository.getAllEvents()
    }

    fun fetchLikedEvents() : LiveData<List<Event>>{
        return  appRepository.getUserLikedEventsAsLiveData()
    }

    fun observeOnEvents(owner : LifecycleOwner, observer : Observer<List<Event>>) = viewModelScope.launch{
        Log.d(TAG, "observeOnData() function called")
        upcomingEventsOutputData.observe(owner, observer)

        // Fetching data from Firebase db
        upcomingEventsOutputData.addSource(fetchUpcomingEvents()) { remoteFetchedEvents ->
            if (remoteFetchedEvents != null){
                Log.d(TAG, "Remote events fetched")
                remoteFetchedEventsTempList.value = remoteFetchedEvents
                fetchIsLikedValueToRemoteEvents()
            }
        }

        // Fetching data from local Room db
        upcomingEventsOutputData.addSource(fetchLikedEvents()) { localUserLikedEvents ->
            if (localUserLikedEvents != null){
                Log.d(TAG, "Local events fetched")
                localUserLikedEventsTempList.value = localUserLikedEvents
                fetchIsLikedValueToRemoteEvents()
            }
        }
    }

    private fun fetchIsLikedValueToRemoteEvents(){
        val outputEventsList = mutableListOf<Event>()
        Log.d(TAG, "Gluing function called")

        if (remoteFetchedEventsTempList.value != null && localUserLikedEventsTempList.value != null){
            for (remoteEvent in remoteFetchedEventsTempList.value!!){
                remoteEvent.isLiked = false
                //Log.d(TAG, "Remote event entered: ${remoteEvent.title}")
                for (localEvent in localUserLikedEventsTempList.value!!){
                    if (remoteEvent.id == localEvent.id){
                        remoteEvent.isLiked = true
                    }
                }
                Log.d(TAG, "Output event added: ${remoteEvent.title}, isLiked:${remoteEvent.isLiked}")
                outputEventsList.add(remoteEvent)
            }

            upcomingEventsOutputData.postValue(outputEventsList)
        }
    }
}
