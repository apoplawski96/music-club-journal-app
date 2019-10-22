package com.example.stk47warehousejournalapp.ui.screens.likedevents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.stk47warehousejournalapp.data.repository.AppRepository

class LikedEventsViewModelFactory (private val appRepository : AppRepository) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LikedEventsViewModel(appRepository) as T
    }
}