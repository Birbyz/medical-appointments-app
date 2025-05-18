package com.example.medicalappointments.networking.models

data class LoginAPIRequestModel(
    val email: String,
    val password: String
)

data class LoginAPIResponseModel(
    val token: String
)