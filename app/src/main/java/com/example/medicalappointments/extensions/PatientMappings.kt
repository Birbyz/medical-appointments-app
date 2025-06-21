package com.example.medicalappointments.extensions

import com.example.medicalappointments.models.Patient
import com.example.medicalappointments.models.PatientEntityModel
import com.example.medicalappointments.models.User

fun PatientEntityModel.toModel(user: User): Patient = Patient(
    id = id,
    birthdate = birthdate,
    user = user
)

fun Patient.toEntity(): PatientEntityModel = PatientEntityModel(
    id = id,
    userId = user.id,
    birthdate = birthdate
)