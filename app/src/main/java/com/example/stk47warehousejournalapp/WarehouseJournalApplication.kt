package com.example.stk47warehousejournalapp

import android.app.Application
import com.example.stk47warehousejournalapp.data.network.FakeDatabase
import com.example.stk47warehousejournalapp.data.network.FakeDatabaseImpl
import com.example.stk47warehousejournalapp.data.network.FirestoreRepository
import com.example.stk47warehousejournalapp.data.network.FirestoreRepositoryImpl
import com.example.stk47warehousejournalapp.ui.upcomingevents.UpcomingEventsViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class WarehouseJournalApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        bind<FirestoreRepository>() with singleton { FirestoreRepositoryImpl() }
        bind<FakeDatabase>() with singleton { FakeDatabaseImpl() }
        bind() from provider { UpcomingEventsViewModelFactory(instance(), instance())}
    }
}