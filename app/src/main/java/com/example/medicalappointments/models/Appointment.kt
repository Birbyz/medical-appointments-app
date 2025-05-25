package com.example.medicalappointments.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.data.models.Doctor
import java.time.LocalDateTime

// abstract class which allows a special set of subclasses
@Entity(tableName = "appointments")
data class Appointment (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val description: String,
    @Embedded(prefix = "patient_")
    val patient: Patient,
    @Embedded(prefix = "doctor_")
    val doctor: Doctor,
    val date: LocalDateTime,
    val category: Category
)

