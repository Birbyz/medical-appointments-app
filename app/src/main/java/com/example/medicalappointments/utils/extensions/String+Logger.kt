package com.example.medicalappointments.utils.extensions

import android.util.Log

// FUNCTIE DE DEBUG
fun String.logErrorMessage(tag: String = "TAG") {
    Log.e("TAG", this)
}