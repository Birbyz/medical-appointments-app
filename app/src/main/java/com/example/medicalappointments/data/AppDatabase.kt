package com.example.medicalappointments.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.data.dao.AppointmentDAO
import com.example.medicalappointments.data.dao.CategoryDAO
import com.example.medicalappointments.data.dao.DoctorDAO
import com.example.medicalappointments.data.dao.PatientDAO
import com.example.medicalappointments.data.dao.SpecialtyDAO
import com.example.medicalappointments.data.dao.UserDAO
import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.data.models.CategoryEntityModel
import com.example.medicalappointments.data.models.DoctorEntityModel
import com.example.medicalappointments.data.models.SpecialtyEntityModel
import com.example.medicalappointments.data.models.UserEntityModel
import com.example.medicalappointments.models.PatientEntityModel
import com.example.medicalappointments.models.Specialty
import kotlinx.coroutines.runBlocking
import java.util.concurrent.Executors

@Database(
    entities = [
        AppointmentEntityModel::class,
        UserEntityModel::class,
        PatientEntityModel::class,
        DoctorEntityModel:: class,
        CategoryEntityModel:: class,
        SpecialtyEntityModel:: class
    ],
    version = 1
)

@TypeConverters(
    AppDatabaseConverter::class
)
abstract class AppDatabase: RoomDatabase() {
    abstract val appointmentDAO: AppointmentDAO
    abstract val userDAO: UserDAO
    abstract val patientDAO: PatientDAO
    abstract val doctorDAO: DoctorDAO
    abstract val categoryDAO: CategoryDAO
    abstract val specialtyDAO: SpecialtyDAO

    companion object {
        fun build(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "medical_appointments_db"
            )
                .fallbackToDestructiveMigration()
                .addCallback(object : Callback() {
                    override fun onOpen(db: SupportSQLiteDatabase) {
                        super.onOpen(db)
                        Executors.newSingleThreadExecutor().execute {
                            val instance = ApplicationController.instance?.appDatabase

                            val specialtyDao = instance?.specialtyDAO
                            val categoryDao = instance?.categoryDAO

                            runBlocking {
                                val existingSpecialties = specialtyDao?.getAll()
                                if (existingSpecialties.isNullOrEmpty()) {
                                    specialtyDao?.insertAll(SpecialtyEntityModel.defaultSpecialtySeed())
                                }

                                val existingCategories = categoryDao?.getAll()
                                if (existingCategories.isNullOrEmpty()) {
                                    categoryDao?.insertAll(
                                        CategoryEntityModel.defaultCategorySeed(context)
                                    )
                                }
                            }
                        }
                    }
                })
                .build()
        }
    }
}