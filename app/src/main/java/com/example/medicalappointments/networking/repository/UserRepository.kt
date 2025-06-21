package com.example.medicalappointments.networking.repository

import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.extensions.toModel
import com.example.medicalappointments.managers.SharedPrefsManager
import com.example.medicalappointments.networking.api.UserApiService
import com.example.medicalappointments.networking.client.RetrofitClient
import com.example.medicalappointments.networking.models.LoginAPIRequestModel

object UserRepository {
    suspend fun getByEmail(email: String) =
        ApplicationController.instance?.appDatabase?.userDAO?.getByEmail(email)?.toModel()
}