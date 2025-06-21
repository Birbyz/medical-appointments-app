package com.example.medicalappointments.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

data class Patient (
    val id: Long = 0,
    val user: User,
    var birthdate: LocalDate
)
