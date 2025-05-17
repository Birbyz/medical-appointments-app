package com.example.medicalappointments.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.medicalappointments.models.Appointment

@Dao
interface AppointmentDAO {
    @Insert
    suspend fun insert(appointment: Appointment)
}