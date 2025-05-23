package com.example.medicalappointments.utils.extensions

import android.content.Context
import android.util.Log
import android.widget.Toast

// FUNCTIE DE DEBUG
fun String.logErrorMessage(tag: String = "TAG") {
    Log.e("TAG", this)
}

fun String.showToast(context: Context) = Toast.makeText(context, this, Toast.LENGTH_SHORT).show()