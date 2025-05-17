package com.example.medicalappointments.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.medicalappointments.data.dao.AppointmentDAO
import com.example.medicalappointments.data.models.AppointmentEntityModel

@Database(
    entities = [AppointmentEntityModel::class],
    version = 1
)

abstract class AppDatabase: RoomDatabase() {
    abstract val appointmentDAO: AppointmentDAO
}