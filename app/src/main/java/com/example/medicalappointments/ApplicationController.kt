package com.example.medicalappointments

import android.app.Application
import androidx.room.Room
import com.example.medicalappointments.data.AppDatabase

class ApplicationController: Application() {
    // everything is static inside the companion
    companion object{
        var instance: ApplicationController? = null
            private set // this param can be accessed outside the companion, yet it cannot be modified
    }

    lateinit var appDatabase: AppDatabase

    // ONCREATE
    override fun onCreate() {
        super.onCreate()
        instance = this
        initDatabase()
    }

    // DATABASE INITIALISATION
    private fun initDatabase() {
        appDatabase = Room.databaseBuilder(
            context = this,
            klass = AppDatabase::class.java,
            name = "localDatabase"
        )
            .fallbackToDestructiveMigration() //this wapes out the database in case of failed migration
            .build()
    }
}