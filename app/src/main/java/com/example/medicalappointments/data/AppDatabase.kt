package com.example.medicalappointments.data

import com.example.medicalappointments.data.models.Doctor
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.medicalappointments.data.dao.AppointmentDAO
import com.example.medicalappointments.data.dao.CategoryDAO
import com.example.medicalappointments.data.dao.DoctorDAO
import com.example.medicalappointments.data.dao.PatientDAO
import com.example.medicalappointments.data.dao.UserDAO
import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.data.models.CategoryEntityModel
import com.example.medicalappointments.models.Patient
import com.example.medicalappointments.models.UserModel

@Database(
    entities = [
        AppointmentEntityModel::class,
        CategoryEntityModel::class,
        UserModel::class,
        Patient::class,
        Doctor:: class
    ],
    version = 5
)

@TypeConverters(
    AppDatabaseConverter::class
)
abstract class AppDatabase: RoomDatabase() {
    abstract val appointmentDAO: AppointmentDAO
    abstract val categoryDAO: CategoryDAO
    abstract val userDAO: UserDAO
    abstract val patientDAO: PatientDAO
    abstract val doctorDAO: DoctorDAO
}