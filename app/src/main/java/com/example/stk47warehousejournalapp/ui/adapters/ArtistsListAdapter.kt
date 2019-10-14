package com.example.stk47warehousejournalapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stk47warehousejournalapp.R
import com.example.stk47warehousejournalapp.data.model.Artist
import com.example.stk47warehousejournalapp.data.model.Event
import kotlinx.android.synthetic.main.item_dj_avatar.view.*
import kotlinx.android.synthetic.main.item_event.view.*

class ArtistsListAdapter (var artistsList : List<Artist>, val clickListener: (Artist) -> Unit) : RecyclerView.Adapter<ArtistsListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.item_dj_avatar, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return artistsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(artistsList[position], clickListener)
    }

    class ViewHolder (itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bind (artist : Artist, clickListener: (Artist) -> Unit){
            itemView.setOnClickListener {clickListener(artist)}
            itemView.artistAvatar_ImageView.setImageResource(artist.avatarResource)
            //itemView.menu_item_image.setImageResource(menuItem.itemImage)
        }
    }

}