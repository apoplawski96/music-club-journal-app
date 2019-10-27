package com.example.stk47warehousejournalapp

import android.app.Application
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.data.repository.AppRepository
import com.example.stk47warehousejournalapp.data.repository.IFirestoreRepository
import com.example.stk47warehousejournalapp.ui.screens.upcomingevents.UpcomingEventsViewModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.junit.Before
import org.junit.Rule
import org.junit.Test
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

    private lateinit var eventsEmptyList : List<Event>
    private lateinit var eventsList : List<Event>
    private lateinit var onFetchedEventsObserver : Observer<List<Event>>

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
        `when`<Context>(context.applicationContext).thenReturn(context)
        viewModel = UpcomingEventsViewModel(appRepository, firestoreRepository)

        mockData()
        setupObservers()
    }

    @Test
    fun upcomingEventsAreEmpty(){
        with (viewModel){
            fetchUpcomingEvents()
            remoteFetchedEventsTempList.observeForever(onFetchedEventsObserver)
        }
        //verify(firestoreRepository).getAllEvents()
    }



    private fun mockData(){
        eventsEmptyList = emptyList()
        val mockList : MutableList<Event> = mutableListOf()
        mockList.add(Event( "2", "NewOldRave w. Carla Roca", "Acid techno/hard techno", "23/04", "Krakow", R.mipmap.event_image_2, false, false))
        mockList.add(Event( "1", "Pandora × STK47 Warehouse Party", "Techno/trance", "22/04", "Krakow", R.mipmap.event_image_1, false, false))
        mockList.add(Event( "3", "We are Radar!", "House/Techno/Dance", "29/04", "Krakow", R.mipmap.event_image_3, false, false))
        mockList.add(Event( "4", "Hi-Fi DUBtwice #23", "Dub/Bass/Drumn'bass", "30/04", "Krakow", R.mipmap.event_image_4, false, false))
        eventsList = mockList.toList()
    }

    private fun setupObservers(){
        onFetchedEventsObserver = mock() as Observer<List<Event>>
    }
}