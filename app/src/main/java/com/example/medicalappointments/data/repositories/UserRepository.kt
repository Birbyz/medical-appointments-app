package com.example.medicalappointments.data.repositories

import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.data.models.UserEntityModel
import com.example.medicalappointments.models.User

object UserRepository {
    suspend fun insertAndReturnId(user: UserEntityModel): Long = ApplicationController.instance?.appDatabase?.userDAO?.insert(user) ?: -1

    suspend fun getAll() = ApplicationController.instance?.appDatabase?.userDAO?.getAll() ?: listOf()

    suspend fun getByEmail(email: String): UserEntityModel? = ApplicationController.instance?.appDatabase?.userDAO?.getByEmail(email)
}