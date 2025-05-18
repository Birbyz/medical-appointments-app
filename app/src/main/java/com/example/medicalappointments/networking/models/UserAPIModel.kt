package com.example.medicalappointments.networking.models

import com.example.medicalappointments.models.UserModel

data class UserListAPIResponse (
    val page: Int,
    val per_page: Int,
    val total: Int,
    val total_pages: Int,
    val data: List<UserModel>
)