package com.example.stk47warehousejournalapp.ui.screens.upcomingevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stk47warehousejournalapp.data.network.FakeDatabase
import com.example.stk47warehousejournalapp.data.repository.AppRepository
import com.example.stk47warehousejournalapp.data.repository.IFirestoreRepository

class UpcomingEventsViewModelFactory (private val appRepository : AppRepository, private val firestoreRepository : IFirestoreRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpcomingEventsViewModel(appRepository, firestoreRepository) as T
    }
}