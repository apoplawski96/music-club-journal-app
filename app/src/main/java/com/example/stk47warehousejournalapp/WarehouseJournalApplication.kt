package com.example.stk47warehousejournalapp

import android.app.Application
import com.example.stk47warehousejournalapp.data.db.UserLocalData
import com.example.stk47warehousejournalapp.data.network.FakeDatabase
import com.example.stk47warehousejournalapp.data.network.FakeDatabaseImpl
import com.example.stk47warehousejournalapp.data.repository.AppRepository
import com.example.stk47warehousejournalapp.data.repository.AppRepositoryImpl
import com.example.stk47warehousejournalapp.data.repository.IFirestoreRepository
import com.example.stk47warehousejournalapp.data.repository.IFirestoreRepositoryImpl
import com.example.stk47warehousejournalapp.ui.screens.likedevents.LikedEventsViewModelFactory
import com.example.stk47warehousejournalapp.ui.screens.upcomingevents.UpcomingEventsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class WarehouseJournalApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@WarehouseJournalApplication))
        bind() from singleton { UserLocalData(instance()) }
        bind() from singleton { instance<UserLocalData>().userLocalDataDao() }
        bind<AppRepository>() with singleton { AppRepositoryImpl(instance()) }
        bind<FakeDatabase>() with singleton { FakeDatabaseImpl() }
        bind<IFirestoreRepository>() with singleton { IFirestoreRepositoryImpl() }
        bind() from provider { UpcomingEventsViewModelFactory(instance(), instance()) }
        bind() from provider { LikedEventsViewModelFactory(instance()) }
    }
}