package com.example.medicalappointments.models

import com.example.medicalappointments.data.models.Doctor
import java.time.LocalDateTime

sealed class Appointment (
    open val title: String,
    open val patient: Patient,
    open val doctor: Doctor,
    open val date: LocalDateTime,
    open val description: String,
    val categoryType: CategoryType
)

data class FollowUpAppointment(
    override val title: String,
    override val description: String,
    override val patient: Patient,
    override val doctor: Doctor,
    override val date: LocalDateTime,
    val imageUrl: String = "https://static.thenounproject.com/png/1072808-200.png"
): Appointment(
    title = title,
    description = description,
    patient = patient,
    doctor = doctor,
    date = date,
    categoryType = CategoryType.FOLLOW_UP
)

data class RegularAppointment(
    override val title: String,
    override val description: String,
    override val patient: Patient,
    override val doctor: Doctor,
    override val date: LocalDateTime,
    //val imageUrl: String = "https://cdn-icons-png.freepik.com/512/10620/10620193.png"
): Appointment(
    title = title,
    description = description,
    patient = patient,
    doctor = doctor,
    date = date,
    categoryType = CategoryType.REGULAR
)

data class SurgeryAppointment(
    override val title: String,
    override val description: String,
    override val patient: Patient,
    override val doctor: Doctor,
    override val date: LocalDateTime,
    //val imageUrl: String = "https://thumbs.dreamstime.com/b/surgery-abdominal-cavity-black-line-icon-surgical-emergency-pictogram-web-page-mobile-app-promo-editable-stroke-surgery-188215511.jpg"
): Appointment(
    title = title,
    description = description,
    patient = patient,
    doctor = doctor,
    date = date,
    categoryType = CategoryType.SURGERY
)

data class VideoAppointment(
    override val title: String,
    override val description: String,
    override val patient: Patient,
    override val doctor: Doctor,
    override val date: LocalDateTime,
    //val imageUrl: String = "https://t3.ftcdn.net/jpg/14/07/40/44/360_F_1407404427_CzBG0ZgUxYNMoF7buZmsFHw1DSPkJpMa.jpg"
): Appointment(
    title = title,
    description = description,
    patient = patient,
    doctor = doctor,
    date = date,
    categoryType = CategoryType.VIDEO
)