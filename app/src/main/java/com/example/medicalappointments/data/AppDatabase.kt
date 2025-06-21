package com.example.medicalappointments.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
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

@Database(
    entities = [
        AppointmentEntityModel::class,
        UserEntityModel::class,
        PatientEntityModel::class,
        DoctorEntityModel:: class,
        CategoryEntityModel:: class,
        SpecialtyEntityModel:: class
    ],
    version = 7
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
}