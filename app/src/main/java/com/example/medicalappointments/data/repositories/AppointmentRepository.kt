package com.example.medicalappointments.data.repositories

import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.data.models.AppointmentEntityModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object AppointmentRepository {

    fun insert(entityModel: AppointmentEntityModel) {
        CoroutineScope(Dispatchers.IO).launch {
            // creates a new thread
            // allows getting methods from DAO without blocking the main thread
            ApplicationController.instance?.appDatabase?.appointmentDAO?.insert(entityModel)
        }
    }
}