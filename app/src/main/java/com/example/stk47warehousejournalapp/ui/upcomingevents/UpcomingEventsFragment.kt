package com.example.stk47warehousejournalapp.ui.upcomingevents

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

import com.example.stk47warehousejournalapp.R
import com.example.stk47warehousejournalapp.data.model.Artist
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.ui.MainActivity
import com.example.stk47warehousejournalapp.ui.adapters.ArtistsListAdapter
import com.example.stk47warehousejournalapp.ui.adapters.EventsListAdapter
import kotlinx.android.synthetic.main.upcoming_events_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class UpcomingEventsFragment : Fragment(), KodeinAware {
    // Basic
    private val TAG = "UpcomingEventsFragment"
    // ViewModel setup
    private val viewModelFactory: UpcomingEventsViewModelFactory by instance()
    private lateinit var viewModel: UpcomingEventsViewModel
    // Kodein
    override val kodein by closestKodein()
    // Collections
    private var upcomingEvents : List<Event>? = null
    private var eventsLineUp : List<Artist>? = null
    // Adapter
    private var adapter : EventsListAdapter? = null
    private var artistsListAdapter : ArtistsListAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.upcoming_events_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpcomingEventsViewModel::class.java)

        bindUI()
    }

    private fun bindUI(){
        // Setting up events list
        /*viewModel.getUpcomingEvents().observe(this, Observer { fetchedEvents ->
            upcomingEvents = fetchedEvents
            test.text = upcomingEvents!![0].id
        })*/
        viewModel.getFakeEvents().observe(this, Observer { fetchedEvents ->
            if (fetchedEvents == null) return@Observer
            upcomingEvents = fetchedEvents
            setupEventsRecyclerView(upcomingEvents!!)
            //test.text = upcomingEvents!![0].id
        })
        // Setting up dj's list
        viewModel.getFakeArtist().observe(this, Observer { fetchedArtist ->
            if (fetchedArtist == null) return@Observer
            eventsLineUp = fetchedArtist
            setupArtistsListRecyclerView(eventsLineUp!!)
        })
    }

    private fun setupEventsRecyclerView(eventsList : List<Event>){
        upcomingEvents_recyclerView.layoutManager = LinearLayoutManager(MainActivity(), RecyclerView.VERTICAL, false)
        adapter = EventsListAdapter(eventsList) { eventItem : Event -> eventItemClicked(eventItem)}
        upcomingEvents_recyclerView.adapter = adapter
    }

    private fun setupArtistsListRecyclerView(artistsList : List<Artist>){
        eventLineUp_recyclerView.layoutManager = LinearLayoutManager(MainActivity(), RecyclerView.HORIZONTAL, false)
        artistsListAdapter = ArtistsListAdapter(artistsList) {artist: Artist -> artistItemClicked(artist)}
        eventLineUp_recyclerView.adapter = artistsListAdapter
    }

    companion object {
        fun newInstance() =
            UpcomingEventsFragment()
    }

    fun eventItemClicked(event : Event){

    }

    fun artistItemClicked(artist : Artist){

    }
}
