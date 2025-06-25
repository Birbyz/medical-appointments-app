package com.example.medicalappointments.data.repositories

import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.models.Appointment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object AppointmentRepository {

    suspend fun getAll() = ApplicationController.instance?.appDatabase?.appointmentDAO?.getAll() ?: listOf()

    fun insert(appointment: AppointmentEntityModel) {
        CoroutineScope(Dispatchers.IO).launch {
            // creates a new thread
            // allows getting methods from DAO without blocking the main thread
            ApplicationController.instance?.appDatabase?.appointmentDAO?.insertAppointment(appointment)
        }
    }

    suspend fun insertAll(appointments: List<AppointmentEntityModel>) {
        ApplicationController.instance?.appDatabase?.appointmentDAO?.insertAppointments(appointments)
    }

    suspend fun delete(id: Long) {
        val appointment = ApplicationController.instance?.appDatabase?.appointmentDAO?.getById(id)
        if (appointment != null) {
            ApplicationController.instance?.appDatabase?.appointmentDAO?.deleteById(appointment.id)
        }
    }
}