package com.example.medicalappointments.models

import com.example.medicalappointments.data.models.Doctor
import java.time.LocalDateTime

// abstract class which allows a special set of subclasses
data class Appointment (
    val id: Long = 0,
    val title: String,
    val description: String,
    val patient: Patient,
    val doctor: Doctor,
    val date: LocalDateTime,
    val category: Category
)