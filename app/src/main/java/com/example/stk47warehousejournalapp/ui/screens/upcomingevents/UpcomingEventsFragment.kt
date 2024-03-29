package com.example.stk47warehousejournalapp.ui.screens.upcomingevents

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stk47warehousejournalapp.internal.BaseFragment
import kotlinx.coroutines.launch
import com.example.stk47warehousejournalapp.R
import com.example.stk47warehousejournalapp.data.model.Artist
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.ui.MainActivity
import com.example.stk47warehousejournalapp.ui.adapters.ArtistsListAdapter
import com.example.stk47warehousejournalapp.ui.adapters.EventsListAdapter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.toObservable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.upcoming_events_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class UpcomingEventsFragment : BaseFragment(), KodeinAware, EventsListAdapter.IOnEventItemClickedInterface {

    val TAG = "UpcomingEventsFragment"

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
    private var eventItemClickedInterface : EventsListAdapter.IOnEventItemClickedInterface = this

    companion object {
        fun newInstance() =
            UpcomingEventsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.upcoming_events_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(UpcomingEventsViewModel::class.java)
        bindUI()
        setupOnClickEvents()
    }

    private fun bindUI(){
        setupEventsRecyclerView()
        fetchUpcomingEvents()
    }

    private fun fetchUpcomingEvents() {
        viewModel.observeOnEvents(this, Observer { fetchedEvents ->
            if (fetchedEvents == null) return@Observer
            upcomingEvents = fetchedEvents
            adapter!!.setItems(upcomingEvents!!)
        })
    }

    private fun setupOnClickEvents(){ }

    private fun setupEventsRecyclerView(){
        upcomingEvents_recyclerView.layoutManager = LinearLayoutManager(MainActivity(), RecyclerView.VERTICAL, false)
        adapter = EventsListAdapter(mutableListOf()) { event: Event -> eventItemClicked(event) }
        adapter!!.setOnItemClickedListener(eventItemClickedInterface)
        upcomingEvents_recyclerView.adapter = adapter
    }

    private fun setupArtistsListRecyclerView(artistsList : List<Artist>){
        eventLineUp_recyclerView.layoutManager = LinearLayoutManager(MainActivity(), RecyclerView.HORIZONTAL, false)
        artistsListAdapter = ArtistsListAdapter(artistsList) {artist: Artist -> artistItemClicked(artist)}
        eventLineUp_recyclerView.adapter = artistsListAdapter
    }

    private fun eventItemClicked(event : Event){
        Toast.makeText(activity, "${event.title} event clicked", Toast.LENGTH_SHORT).show()
    }

    override fun onEventHeartIconClicked(event: Event, view : View) {
        if (!event.isLiked){
            Log.d(TAG, "HeartClick - addEvent")
            viewModel.addLikedEvent(event)
            eventItemClickedInterface.setHeartIconChecked(view)
            Toast.makeText(activity, "Added to liked events", Toast.LENGTH_SHORT).show()
        } else if (event.isLiked) {
            Log.d(TAG, "HeartClick remove event")
            viewModel.removeLikedEvent(event)
            eventItemClickedInterface.setHeartIconEmpty(view)
            Toast.makeText(activity, "Event removed from liked", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onEventShared(event: Event, view: View) { }
    private fun artistItemClicked(artist : Artist){ }
}
