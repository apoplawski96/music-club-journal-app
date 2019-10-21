package com.example.stk47warehousejournalapp.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "liked_event")
data class Event(
    @PrimaryKey
    @SerializedName("id")
    var id : Int? = null,
    @SerializedName("title")
    val title : String,
    @SerializedName("genre")
    val genre : String,
    @SerializedName("date")
    val date : String,
    @SerializedName("location")
    val location : String,
    val eventImageResource : Int,
    @SerializedName("is_past")
    val isPast : Boolean,
    @SerializedName("is_liked")
    val isLiked : Boolean
)

/*data class Event(val id : String? = null,
                 val title : String,
                 val description : String,
                 val location : String,
                 val mainGenre : String,
                 val genres : Array<String>?,
                 val djs : Array<String>,
                 val startTime : String,
                 val endTime : String,
                 val date : String) : Serializable*/