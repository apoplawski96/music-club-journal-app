package com.example.stk47warehousejournalapp.data.network

import com.example.stk47warehousejournalapp.R
import com.example.stk47warehousejournalapp.data.model.Artist
import com.example.stk47warehousejournalapp.data.model.Event
import kotlinx.coroutines.delay

class FakeDatabaseImpl : FakeDatabase {
    override var fakeEvents: List<Event> = ArrayList()
    override var fakeArtists : List<Artist> = ArrayList()

    override fun getUpcomingEvents(): List<Event> {
        populateFakeEventsList()
        return fakeEvents
    }

    override fun getArtistsList(): List<Artist> {
        populateFakeArtistsList()
        return fakeArtists
    }

    override fun populateFakeEventsList() {
        fakeEvents = listOf(
            Event( 1, "Pandora × STK47 Warehouse Party", "Techno/trance", "22/04", "Krakow", R.mipmap.event_image_1, false, false),
            Event( 2, "NewOldRave w. Carla Roca", "Acid techno/hard techno", "23/04", "Krakow", R.mipmap.event_image_2, false, false),
            Event( 3, "We are Radar!", "House/Techno/Dance", "29/04", "Krakow", R.mipmap.event_image_3, false, false),
            Event( 4, "Hi-Fi DUBtwice #23", "Dub/Bass/Drumn'bass", "30/04", "Krakow", R.mipmap.event_image_4, false, false))
    }

    override fun populateFakeArtistsList() {
        fakeArtists = listOf(
            Artist("1", R.mipmap.dj_avatar_1),
            Artist("2", R.mipmap.dj_avatar_2),
            Artist("3", R.mipmap.dj_avatar_3),
            Artist("4", R.mipmap.dj_avatar_4),
            Artist("5", R.mipmap.dj_avatar_5))
    }
}