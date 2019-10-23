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

    fun observeOnData(owner : LifecycleOwner, observer : Observer<List<Event>>) = viewModelScope.launch{

        upcomingEventsOutputData.observe(owner, observer)

        // Fetching data from Firebase db
        upcomingEventsOutputData.addSource(firestoreRepository.getAllEvents()) { remoteFetchedEvents ->
            if (remoteFetchedEvents != null){
                remoteFetchedEventsTempList.value = remoteFetchedEvents
                fetchIsLikedValueToRemoteEvents()
            }
        }

        // Fetching data from local Room db
        lazy {
            upcomingEventsOutputData.addSource(appRepository.getUserLikedEventsAsLiveData()) { localUserLikedEvents ->
                if (localUserLikedEvents != null){
                    localUserLikedEventsTempList.value = localUserLikedEvents
                    fetchIsLikedValueToRemoteEvents()
                }
            }
        }
    }

    fun addLikedEvent(event: Event){
        appRepository.addLikedEvent(event)
    }

    private fun fetchIsLikedValueToRemoteEvents(){
        upcomingEventsOutputData.postValue(remoteFetchedEventsTempList.value)
    }
}
