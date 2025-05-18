package com.example.medicalappointments.networking.api

import com.example.medicalappointments.networking.models.UserListAPIResponse
import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.http.Headers

interface UserApiService{
    @GET("/api/users")
    @Headers("x-api-key: reqres-free-v1")
    suspend fun getUsers(@Query("page") page: Int): UserListAPIResponse
}
