package com.example.medicalappointments.networking.api

import com.example.medicalappointments.networking.models.UserListAPIResponse
import retrofit2.http.Query
import retrofit2.http.GET

interface UserApiService{
    @GET("/api/users")
    suspend fun getUsers(@Query("page") page: Int): UserListAPIResponse
}
