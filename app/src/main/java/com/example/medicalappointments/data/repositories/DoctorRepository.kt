package com.example.medicalappointments.data.repositories

import com.example.medicalappointments.data.models.Doctor
import com.example.medicalappointments.ApplicationController

object DoctorRepository {
    suspend fun getAll() = ApplicationController.instance?.appDatabase?.doctorDAO?.getAll() ?: listOf()

    suspend fun insertDoctors(entities: List<Doctor>){
        ApplicationController.instance?.appDatabase?.doctorDAO?.insertDoctors(entities)
    }
}