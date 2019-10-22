package com.example.stk47warehousejournalapp.internal

import android.content.Context
import android.net.Uri
import com.example.stk47warehousejournalapp.R
import com.example.stk47warehousejournalapp.data.model.Event
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

object Utils {
    fun buildVideoUri(context : Context) : Uri {
        return Uri.parse(
            "android.resource://"
                    + context.packageName
                    + "/"
                    + R.raw.background_vid
        )
    }

    fun addEventsListToFirebase(){
        val db = FirebaseFirestore.getInstance()
        val eventsRef = db.collection("events")
        val eventsList = listOf(
            Event( "1", "Pandora × STK47 Warehouse Party", "Techno/trance", "22/04", "Krakow", R.mipmap.event_image_1, false, false),
            Event( "2", "NewOldRave w. Carla Roca", "Acid techno/hard techno", "23/04", "Krakow", R.mipmap.event_image_2, false, false),
            Event( "3", "We are Radar!", "House/Techno/Dance", "29/04", "Krakow", R.mipmap.event_image_3, false, false),
            Event( "4", "Hi-Fi DUBtwice #23", "Dub/Bass/Drumn'bass", "30/04", "Krakow", R.mipmap.event_image_4, false, false)
        )
        db.runBatch { batch ->
            for (event in eventsList){
                val eventRef = eventsRef.document(event.id.toString())
                batch.set(eventRef, event)
            }
        }
    }
}