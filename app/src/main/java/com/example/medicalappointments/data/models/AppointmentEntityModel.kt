package com.example.medicalappointments.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppointmentEntityModel (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String
)