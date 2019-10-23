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
    private var remoteFetchedEventsTempList = MutableLiveData<List<Event>>()
    private var localUserLikedEventsTempList = MutableLiveData<List<Event>>()

    fun addLikedEvent(event: Event){
        appRepository.addLikedEvent(event)
    }

    fun observeOnData(owner : LifecycleOwner, observer : Observer<List<Event>>) = viewModelScope.launch{

        upcomingEventsOutputData.observe(owner, observer)

        // Fetching data from Firebase db
        upcomingEventsOutputData.addSource(firestoreRepository.getAllEvents()) { remoteFetchedEvents ->
            if (remoteFetchedEvents != null){
                Log.d(TAG, "Remote events fetched")
                remoteFetchedEventsTempList.value = remoteFetchedEvents
                fetchIsLikedValueToRemoteEvents()
            }
        }

        // Fetching data from local Room db
        upcomingEventsOutputData.addSource(appRepository.testGetEvents()) { localUserLikedEvents ->
            if (localUserLikedEvents != null){
                Log.d(TAG, "Local events fetched")
                localUserLikedEventsTempList.value = localUserLikedEvents
                fetchIsLikedValueToRemoteEvents()
            }
        }
    }

    private fun fetchIsLikedValueToRemoteEvents(){
        val outputEventsList = mutableListOf<Event>()

        if (remoteFetchedEventsTempList.value != null && localUserLikedEventsTempList.value != null){
            for (remoteEvent in remoteFetchedEventsTempList.value!!){
                Log.d(TAG, "Remote event entered: ${remoteEvent.title}")
                for (localEvent in localUserLikedEventsTempList.value!!){
                    if (remoteEvent.id == localEvent.id){
                        Log.d(TAG, "Matching local event found: ${localEvent.id} = ${remoteEvent.id}")
                        remoteEvent.isLiked = true
                    }
                }
                Log.d(TAG, "Output event added: ${remoteEvent.title}, isLiked:${remoteEvent.isLiked}")
                outputEventsList.add(remoteEvent)
            }

            for (outputItem in outputEventsList){
                Log.d(TAG, outputItem.toString())
            }

            upcomingEventsOutputData.postValue(outputEventsList)
        }
    }
}
