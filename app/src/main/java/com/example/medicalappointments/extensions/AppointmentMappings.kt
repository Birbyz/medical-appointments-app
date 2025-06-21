package com.example.medicalappointments.extensions

import com.example.medicalappointments.data.models.AppointmentEntityModel
import com.example.medicalappointments.models.Appointment
import com.example.medicalappointments.models.Category
import com.example.medicalappointments.models.Doctor
import com.example.medicalappointments.models.Patient

fun AppointmentEntityModel.toModel(
    patient: Patient,
    doctor: Doctor,
    category: Category
): Appointment = Appointment(
    id = id,
    title = title,
    description = description,
    patient = patient,
    doctor = doctor,
    date = date,
    category = category,
)

fun Appointment.toEntity(): AppointmentEntityModel = AppointmentEntityModel(
    id = id,
    title = title,
    description = description,
    patientId = patient.id,
    doctorId = doctor.id,
    date = date,
    categoryId = category.id,
)