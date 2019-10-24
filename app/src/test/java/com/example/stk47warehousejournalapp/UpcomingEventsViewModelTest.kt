package com.example.stk47warehousejournalapp

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.stk47warehousejournalapp.data.repository.AppRepository
import com.example.stk47warehousejournalapp.data.repository.IFirestoreRepository
import com.example.stk47warehousejournalapp.ui.screens.upcomingevents.UpcomingEventsViewModel
import com.nhaarman.mockitokotlin2.mock
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class UpcomingEventsViewModelTest {

    @Mock
    private lateinit var context: Application

    @Rule
    @JvmField
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel : UpcomingEventsViewModel

    private val appRepository : AppRepository = mock()
    private val firestoreRepository : IFirestoreRepository = mock()

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        `when`<Context>(context.applicationContext).thenReturn(context)
        viewModel = UpcomingEventsViewModel(appRepository, firestoreRepository)
    }

}