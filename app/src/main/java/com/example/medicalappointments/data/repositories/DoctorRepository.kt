package com.example.medicalappointments.data.repositories

import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.data.models.DoctorEntityModel
import com.example.medicalappointments.models.Doctor

object DoctorRepository {
    suspend fun getAll() = ApplicationController.instance?.appDatabase?.doctorDAO?.getAll() ?: listOf()

    suspend fun insertDoctors(entities: List<DoctorEntityModel>){
        ApplicationController.instance?.appDatabase?.doctorDAO?.insertDoctors(entities)
    }
}