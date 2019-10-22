package com.example.stk47warehousejournalapp.ui.likedevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.data.repository.AppRepository
import io.reactivex.Observable
import kotlinx.coroutines.launch

class LikedEventsViewModel() : ViewModel() {
    private val TAG = "LikedEventsViewModel"
    lateinit var mAppRepository: AppRepository

    constructor(appRepository: AppRepository) : this(){
        mAppRepository = appRepository
    }

    fun addLikedEvent (event : Event) = viewModelScope.launch {
        mAppRepository.addLikedEvent(event)
    }

    fun getLikedEvents() : Observable<List<Event>>{
        return mAppRepository.getLikedEventsInObservable()
    }
}
