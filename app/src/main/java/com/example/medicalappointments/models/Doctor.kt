package com.example.medicalappointments.models

// used for views, API calls, UI
data class Doctor(
    val id: Long = 0,
    val user: User,
    val yearsOfExperience: Int,
    val specialty: Specialty
)