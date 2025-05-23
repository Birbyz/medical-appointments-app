package com.example.medicalappointments.models

import com.example.medicalappointments.data.models.Doctor
import java.time.LocalDateTime

data class Appointments(
    var title: String,
    var patient: Patient,
    var doctor: Doctor,
    var date: LocalDateTime,
    var description: String
)
