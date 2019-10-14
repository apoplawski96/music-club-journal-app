package com.example.stk47warehousejournalapp.data.network

import com.example.stk47warehousejournalapp.data.model.Artist
import com.example.stk47warehousejournalapp.data.model.Event

interface FakeDatabase {
    var fakeEvents : List<Event>
    var fakeArtists : List<Artist>

    fun getUpcomingEvents() : List<Event>
    fun getArtistsList() : List<Artist>

    // Sample data stuff
    fun populateFakeEventsList()
    fun populateFakeArtistsList()

}