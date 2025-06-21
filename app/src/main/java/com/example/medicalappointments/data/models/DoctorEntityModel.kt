package com.example.medicalappointments.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors")
data class DoctorEntityModel (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val userId: Long,
    var yearsOfExperience: Int,
    var specialtyId: Long
)

//fun SpecialtyType.getDisplayName(context: Context): String {
//    return context.getString(this.specialty)
//}