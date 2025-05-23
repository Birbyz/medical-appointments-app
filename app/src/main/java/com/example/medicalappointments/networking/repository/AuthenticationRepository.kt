package com.example.medicalappointments.networking.repository

import com.example.medicalappointments.networking.api.AuthenticationApiService
import com.example.medicalappointments.networking.client.RetrofitClient
import com.example.medicalappointments.networking.models.LoginAPIRequestModel

object AuthenticationRepository {
    private val authenticationApiService by lazy {
        RetrofitClient.nonAuthInstance.create(AuthenticationApiService::class.java)
    }

    suspend fun login(email: String, password: String) =
        authenticationApiService.login(LoginAPIRequestModel(email, password))
}