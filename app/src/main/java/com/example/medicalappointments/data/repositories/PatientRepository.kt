package com.example.medicalappointments.data.repositories

import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.models.Patient

object PatientRepository {
    suspend fun getAllPatients() = ApplicationController.instance?.appDatabase?.patientDAO?.getAll() ?: listOf()

    suspend fun insertPatients(entities: List<Patient>) {
        ApplicationController.instance?.appDatabase?.patientDAO?.insertPatients(entities)
    }
}