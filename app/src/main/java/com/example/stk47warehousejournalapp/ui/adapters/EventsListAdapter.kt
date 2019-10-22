package com.example.stk47warehousejournalapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stk47warehousejournalapp.R
import com.example.stk47warehousejournalapp.data.model.Event
import kotlinx.android.synthetic.main.item_event.view.*

class EventsListAdapter (private var eventsList : MutableList<Event>, private val clickListener: (Event) -> Unit) : RecyclerView.Adapter<EventsListAdapter.ViewHolder>(){

    private var mListener : IOnEventItemClickedInterface? = null

    interface IOnEventItemClickedInterface{
        fun onEventLiked(event : Event, view : View)
        fun onEventShared(event : Event, view : View)
        fun setHeartIconChecked(view : View){ view.heartIcon_imageView.setImageResource(R.mipmap.ic_heart_full) }
        fun setHeartIconEmpty(view : View){ view.heartIcon_imageView.setImageResource(R.mipmap.ic_heart_empty) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) { holder.bind(eventsList[position], clickListener, mListener!!) }

    fun setOnItemClickedListener (listener : IOnEventItemClickedInterface){ mListener = listener }

    fun updateItems(newEvents : List<Event>){
        eventsList = newEvents.toMutableList()
        notifyDataSetChanged()
    }

    fun setItems(data: List<Event>) {
        eventsList.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind (eventItem : Event, clickListener: (Event) -> Unit, onClickInterface: IOnEventItemClickedInterface){
            // OnClicks setup
            itemView.setOnClickListener { clickListener(eventItem) }
            itemView.upcomingEvent_heartIcon.setOnClickListener { if (adapterPosition != RecyclerView.NO_POSITION){ onClickInterface.onEventLiked(eventItem, itemView) } }
            // Basic data binding
            itemView.eventCover_imageView.setImageResource(eventItem.eventImageResource)
            itemView.eventItem_title.text = eventItem.title
            itemView.eventItem_genre.text = eventItem.genre
            // Setting up proper heart icon
            if (eventItem.isLiked) onClickInterface.setHeartIconChecked(itemView)
            else onClickInterface.setHeartIconEmpty(itemView)
        }
    }
}