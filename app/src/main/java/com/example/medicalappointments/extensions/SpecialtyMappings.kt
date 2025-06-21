package com.example.medicalappointments.extensions

import com.example.medicalappointments.data.models.SpecialtyEntityModel
import com.example.medicalappointments.models.Specialty

fun SpecialtyEntityModel.toModel(): Specialty = Specialty(
    id = id,
    name = name
)

fun Specialty.toEntity(): SpecialtyEntityModel = SpecialtyEntityModel(
    id = id,
    name = name
)