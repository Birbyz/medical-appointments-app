package com.example.medicalappointments.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.medicalappointments.models.Appointment
import com.example.medicalappointments.models.Category
import com.example.medicalappointments.models.Patient
import java.time.LocalDateTime

@Entity(tableName = "appointments")
data class AppointmentEntityModel (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    val patientId: Long,
    val doctorId: Long,
    val date: LocalDateTime,
    val categoryId: Long
)



