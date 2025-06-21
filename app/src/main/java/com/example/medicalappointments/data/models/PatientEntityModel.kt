package com.example.medicalappointments.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "patients")
data class PatientEntityModel (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    var birthdate: LocalDate
)
