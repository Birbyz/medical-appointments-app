package com.example.medicalappointments

import android.app.Application
import android.content.SharedPreferences
import androidx.room.Room
import com.example.medicalappointments.data.AppDatabase
import com.example.medicalappointments.data.models.SpecialtyEntityModel
import com.example.medicalappointments.managers.SharedPrefsManager
import java.util.concurrent.Executors

class ApplicationController: Application() {
    // everything is static inside the companion
    companion object{
        const val SHARED_PREFS_NAME = "shared_refs"

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
        appDatabase = AppDatabase.build(this)
//        appDatabase = Room.databaseBuilder(
//            context = this,
//            klass = AppDatabase::class.java,
//            name = "localDatabase"
//        )
//            .fallbackToDestructiveMigration() //this wipes out the database in case of failed migration
//            .build()
    }

    val sharedPrefs: SharedPreferences by lazy {
        getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE)
    }
}