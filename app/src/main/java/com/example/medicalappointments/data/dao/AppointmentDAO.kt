package com.example.medicalappointments.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.models.Appointment

@Dao
interface AppointmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAppointment(appointment: Appointment): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAppointments(appointments: List<Appointment>): List<Long>

    @Query("SELECT * FROM appointments")
    suspend fun getAll(): List<Appointment>
}