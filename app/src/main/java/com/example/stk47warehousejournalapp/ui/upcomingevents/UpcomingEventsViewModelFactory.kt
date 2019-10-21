package com.example.stk47warehousejournalapp.ui.upcomingevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stk47warehousejournalapp.data.network.FakeDatabase
import com.example.stk47warehousejournalapp.data.repository.AppRepository

class UpcomingEventsViewModelFactory (private val appRepository : AppRepository, private val fakeDatabase: FakeDatabase) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpcomingEventsViewModel(appRepository, fakeDatabase) as T
    }
}