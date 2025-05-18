package com.example.medicalappointments.networking.repository

import com.example.medicalappointments.managers.SharedPrefsManager
import com.example.medicalappointments.networking.api.UserApiService
import com.example.medicalappointments.networking.client.RetrofitClient
import com.example.medicalappointments.networking.models.LoginAPIRequestModel

object UserRepository {
    private val userApiService by lazy {
        RetrofitClient.authInstance.create(UserApiService::class.java)
    }

    suspend fun getUsers(page: Int) = userApiService.getUsers(page = page)
}