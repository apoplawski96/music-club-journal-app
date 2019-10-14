package com.example.stk47warehousejournalapp.data.model

import java.io.Serializable

data class Event(val id : String? = null,
                 val title : String,
                 val genre : String,
                 val date : String,
                 val location : String,
                 val eventImageResource : Int,
                 val isPast : Boolean)

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