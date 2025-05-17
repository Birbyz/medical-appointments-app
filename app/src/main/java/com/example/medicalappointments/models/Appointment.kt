package com.example.medicalappointments.models

import Doctor
import java.time.LocalDateTime

class Appointment (
    var title: String,
    var pacient: Pacient,
    var doctor: Doctor,
    var date: LocalDateTime,
    var description: String,
    val categoryType: CategoryType
)