package com.example.medicalappointments.models

import com.example.medicalappointments.R

data class Doctor (
    var name: String,
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