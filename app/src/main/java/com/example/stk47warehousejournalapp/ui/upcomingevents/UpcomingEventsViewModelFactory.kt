package com.example.stk47warehousejournalapp.ui.upcomingevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stk47warehousejournalapp.data.network.FakeDatabase
import com.example.stk47warehousejournalapp.data.network.FirestoreRepository

class UpcomingEventsViewModelFactory (private val firestoreRepository : FirestoreRepository, private val fakeDatabase: FakeDatabase) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpcomingEventsViewModel(firestoreRepository, fakeDatabase) as T
    }
}