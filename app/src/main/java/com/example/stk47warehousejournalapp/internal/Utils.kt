package com.example.stk47warehousejournalapp.internal

import android.content.Context
import android.net.Uri
import com.example.stk47warehousejournalapp.R

object Utils {
    fun buildVideoUri(context : Context) : Uri {
        return Uri.parse("android.resource://"
        + context.packageName
        + "/"
        + R.raw.background_vid
        )
    }

}