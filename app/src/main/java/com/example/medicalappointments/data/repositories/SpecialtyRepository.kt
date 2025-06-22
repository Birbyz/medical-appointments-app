package com.example.medicalappointments.data.repositories

import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.data.models.SpecialtyEntityModel

object SpecialtyRepository {
    suspend fun getAll() = ApplicationController.instance?.appDatabase?.specialtyDAO?.getAll() ?: listOf()
}