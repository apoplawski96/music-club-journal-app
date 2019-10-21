package com.example.stk47warehousejournalapp.ui.likedevents

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stk47warehousejournalapp.BaseFragment

import com.example.stk47warehousejournalapp.R
import com.example.stk47warehousejournalapp.data.model.Artist
import com.example.stk47warehousejournalapp.data.model.Event
import com.example.stk47warehousejournalapp.ui.MainActivity
import com.example.stk47warehousejournalapp.ui.adapters.ArtistsListAdapter
import com.example.stk47warehousejournalapp.ui.adapters.EventsListAdapter
import com.example.stk47warehousejournalapp.ui.upcomingevents.UpcomingEventsViewModel
import com.example.stk47warehousejournalapp.ui.upcomingevents.UpcomingEventsViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.liked_events_fragment.*
import kotlinx.android.synthetic.main.upcoming_events_fragment.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class LikedEventsFragment : BaseFragment(), KodeinAware, EventsListAdapter.IOnEventItemClickedInterface {
    // Basic
    private val TAG = "LikedEventsFragment"
    // ViewModel setup
    private val viewModelFactory: LikedEventsViewModelFactory by instance()
    private lateinit var viewModel: LikedEventsViewModel
    // Kodein
    override val kodein by closestKodein()
    // Adapter
    private var adapter : EventsListAdapter? = null
    private var eventItemClickedInterface : EventsListAdapter.IOnEventItemClickedInterface = this
    // RxKotlin
    private val disposable = CompositeDisposable()

    companion object {
        fun newInstance() = LikedEventsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.liked_events_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(LikedEventsViewModel::class.java)
        bindUI()
    }

    private fun bindUI(){
        setupLikedEventsRecyclerView()
    }

    private fun setupLikedEventsRecyclerView(){
        likedEvents_recyclerView.layoutManager = LinearLayoutManager(MainActivity(), RecyclerView.VERTICAL, false)
        adapter = EventsListAdapter(mutableListOf(Event( 1, "Pandora × STK47 Warehouse Party", "Techno/trance", "22/04", "Krakow", R.mipmap.event_image_1, false, false))) { event: Event -> eventItemClicked(event) }
        adapter!!.setOnItemClickedListener(eventItemClickedInterface)
        likedEvents_recyclerView.adapter = adapter
        fetchLikedEvents()
    }

    private fun fetchLikedEvents(){
        disposable.add(viewModel.getLikedEvents()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ events ->
                adapter!!.setItems(events)
            })
    }

    override fun onEventLiked(event: Event, view: View) { }
    override fun onEventShared(event: Event, view: View) { }
    private fun eventItemClicked(event : Event){ }
}
