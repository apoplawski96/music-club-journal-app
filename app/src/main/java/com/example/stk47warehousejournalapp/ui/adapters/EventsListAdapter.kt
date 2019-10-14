package com.example.stk47warehousejournalapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stk47warehousejournalapp.R
import com.example.stk47warehousejournalapp.data.model.Event
import kotlinx.android.synthetic.main.item_event.view.*
import kotlinx.coroutines.processNextEventInCurrentThread

class EventsListAdapter (var eventsList : List<Event>, val clickListener: (Event) -> Unit) : RecyclerView.Adapter<EventsListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return eventsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(eventsList[position], clickListener)
    }

    /*fun updateItems(newTasks : ArrayList<Task>){
        taskList = newTasks
        notifyDataSetChanged()
    }*/

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind (eventItem : Event, clickListener: (Event) -> Unit){
            itemView.setOnClickListener {clickListener(eventItem)}
            itemView.eventCover_imageView.setImageResource(eventItem.eventImageResource)
            itemView.eventItem_title.text = eventItem.title
            itemView.eventItem_genre.text = eventItem.genre
            //itemView.menu_item_image.setImageResource(menuItem.itemImage)
        }
    }
}