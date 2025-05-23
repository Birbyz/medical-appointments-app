package com.example.medicalappointments.data.models

import android.content.Context
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.medicalappointments.R
import com.example.medicalappointments.models.UserModel

@Entity(tableName = "doctors")
data class Doctor (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @Embedded(prefix = "user_")
    val user: UserModel,
    var yearsOfExperience: Int,
    var specialty: SpecialtyType
)

enum class SpecialtyType(val id: Int, val SpecialtyName: Int) {
    OPHTHALMOLOGIST(0, R.string.ophthalmologist),
    GENERAL_PRACTITIONER(1, R.string.general_practitioner),
    CARDIOLOGIST(2, R.string.cardiologist),
    DERMATOLOGIST(3, R.string.dermatologist),
    PEDIATRICIAN(4, R.string.pediatrician),
    SURGEON(5, R.string.surgeon),
    NEUROLOGIST(6, R.string.neurologist),
    PSYCHIATRIST(7, R.string.psychiatrist),
    ORTHOPEDIST(8, R.string.orthopedist),
    ONCOLOGIST(9, R.string.oncologist);
}

fun SpecialtyType.getDisplayName(context: Context): String {
    return context.getString(this.SpecialtyName)
}