package com.example.medicalappointments.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "patients")
data class Patient (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @Embedded(prefix = "user_")
    val user: UserModel,
    var age: Int,
    var birthdate: LocalDate
)
