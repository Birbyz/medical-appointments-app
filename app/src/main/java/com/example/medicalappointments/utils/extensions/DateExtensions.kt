package com.example.medicalappointments.utils.extensions

import java.time.LocalDate
import java.time.Period

fun LocalDate.getAge(): Int {
    return Period.between(this, LocalDate.now()).years
}