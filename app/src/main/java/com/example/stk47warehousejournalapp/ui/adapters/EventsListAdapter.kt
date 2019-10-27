package com.example.stk47warehousejournalapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.stk47warehousejournalapp.R
import com.example.stk47warehousejournalapp.data.model.Event
import kotlinx.android.synthetic.main.item_event.view.*

class EventsListAdapter (private var eventsList : MutableList<Event>, private val clickListener: (Event) -> Unit) : RecyclerView.Adapter<EventsListAdapter.ViewHolder>(){

    private var mListener : IOnEventItemClickedInterface? = null

    interface IOnEventItemClickedInterface{
        fun onEventHeartIconClicked(event : Event, view : View)
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
        val oldList = eventsList.toList()
        val diffResult : DiffUtil.DiffResult = DiffUtil.calculateDiff(
            EventItemDiffCallback(oldList, data)
        )
        eventsList.clear()
        eventsList.addAll(data)
        diffResult.dispatchUpdatesTo(this)
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind (eventItem : Event, clickListener: (Event) -> Unit, onClickInterface: IOnEventItemClickedInterface){
            // OnClicks setup
            itemView.setOnClickListener { clickListener(eventItem) }
            itemView.upcomingEvent_heartIcon.setOnClickListener { if (adapterPosition != RecyclerView.NO_POSITION){ onClickInterface.onEventHeartIconClicked(eventItem, itemView) } }
            // Basic data binding
            itemView.eventCover_imageView.setImageResource(eventItem.eventImageResource)
            itemView.eventItem_title.text = eventItem.title
            itemView.eventItem_genre.text = eventItem.genre
            // Setting up proper heart icon
            if (eventItem.isLiked) itemView.heartIcon_imageView.setImageResource(R.mipmap.ic_heart_full)
            else itemView.heartIcon_imageView.setImageResource(R.mipmap.ic_heart_empty)
        }
    }

    class EventItemDiffCallback(
        var oldEventsList : List<Event>,
        var newEventsList : List<Event>
    ) : DiffUtil.Callback(){
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldEventsList[oldItemPosition].id == newEventsList[newItemPosition].id)
        }

        override fun getOldListSize(): Int {
            return oldEventsList.size
        }

        override fun getNewListSize(): Int {
            return newEventsList.size
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return (oldEventsList[oldItemPosition] == (newEventsList[newItemPosition]))
        }
    }
}