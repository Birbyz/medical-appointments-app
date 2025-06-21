package com.example.medicalappointments.extensions

import com.example.medicalappointments.data.models.DoctorEntityModel
import com.example.medicalappointments.models.Doctor
import com.example.medicalappointments.models.Specialty
import com.example.medicalappointments.models.User

fun DoctorEntityModel.toModel(user: User, specialty: Specialty): Doctor = Doctor(
    id = id,
    user = user,
    yearsOfExperience = yearsOfExperience,
    specialty = specialty
)

fun Doctor.toEntity(): DoctorEntityModel = DoctorEntityModel(
    id = id,
    userId = user.id,
    yearsOfExperience = yearsOfExperience,
    specialtyId = specialty.id
)