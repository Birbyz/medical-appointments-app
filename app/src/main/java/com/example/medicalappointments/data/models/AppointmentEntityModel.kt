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
    @Embedded(prefix = "patient_")
    val patient: Patient,
    @Embedded(prefix = "doctor_")
    val doctor: Doctor,
    val date: LocalDateTime,
    val category: Category
)

fun Appointment.toEntity() = AppointmentEntityModel(
    id = id,
    title = title,
    description = description,
    patient = patient,
    doctor = doctor,
    date = date,
    category = category,
)

fun AppointmentEntityModel.toModel(): Appointment = Appointment(
    id = id,
    title = title,
    description = description,
    patient = patient,
    doctor = doctor,
    date = date,
    category = category,
)