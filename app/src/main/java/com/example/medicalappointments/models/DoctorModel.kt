package com.example.medicalappointments.models

import com.example.medicalappointments.data.models.Doctor
import com.example.medicalappointments.data.models.SpecialtyType

// used for views, API calls, UI
data class DoctorModel(
    val id: Long = 0,
    val user: UserModel,
    val yearsOfExperience: Int,
    val specialty: SpecialtyType
)

// conversion from model to entity
// as an entity, com.example.medicalappointments.data.models.Doctor is a class especially user by Room, basically it is stored locally
fun Doctor.toModel(): DoctorModel = DoctorModel(
    id = id,
    user = user,
    yearsOfExperience = yearsOfExperience,
    specialty = specialty
)

// conversion from entity to model
fun DoctorModel.toEntity(): Doctor = Doctor(
    id = id,
    user = user,
    yearsOfExperience = yearsOfExperience,
    specialty = specialty
)