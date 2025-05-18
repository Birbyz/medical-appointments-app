package com.example.medicalappointments.models

import com.google.gson.annotations.SerializedName

data class UserModel (
    val id: String,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String
)