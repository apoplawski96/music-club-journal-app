package com.example.stk47warehousejournalapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.stk47warehousejournalapp.data.model.Event

@Database(
    entities = [Event::class],
    version = 3,
    exportSchema = false
)
abstract class UserLocalData : RoomDatabase(){

    abstract fun userLocalDataDao() : UserLocalDataDao

    companion object {
        @Volatile
        private var instance : UserLocalData? = null
        private val LOCK = Any()

        operator fun invoke(context : Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                UserLocalData::class.java, "userdata.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}