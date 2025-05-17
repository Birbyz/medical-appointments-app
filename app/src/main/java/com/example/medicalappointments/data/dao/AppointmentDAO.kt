package com.example.medicalappointments.data.dao

import androidx.room.Dao
import androidx.room.Insert
import com.example.medicalappointments.data.models.AppointmentEntityModel

@Dao
interface AppointmentDAO {
    @Insert
    suspend fun insert(appointment: AppointmentEntityModel)
}