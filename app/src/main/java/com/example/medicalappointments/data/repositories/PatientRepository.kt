package com.example.medicalappointments.data.repositories

import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.models.Patient
import com.example.medicalappointments.models.PatientEntityModel

object PatientRepository {
    suspend fun getAll(): List<PatientEntityModel> = ApplicationController.instance?.appDatabase?.patientDAO?.getAll() ?: listOf()

    suspend fun insertPatients(entities: List<PatientEntityModel>) {
        ApplicationController.instance?.appDatabase?.patientDAO?.insertPatients(entities)
    }
}