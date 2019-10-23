package com.example.stk47warehousejournalapp.ui.screens.likedevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.data.repository.AppRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.launch

class LikedEventsViewModel() : ViewModel() {

    private lateinit var mAppRepository: AppRepository

    constructor(appRepository: AppRepository) : this(){
        mAppRepository = appRepository
    }

    fun getLikedEvents() : Observable<List<Event>>{
        return mAppRepository.getUserLikedEventsAsObservable()
    }
}
