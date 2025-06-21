package com.example.medicalappointments.data.repositories

import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.data.models.UserEntityModel
import com.example.medicalappointments.models.User

object UserRepository {
    suspend fun insert(entities: List<UserEntityModel>) {
        // creates a new thread
        // allows getting methods from DAO without blocking the main thread
        ApplicationController.instance?.appDatabase?.userDAO?.insert(entities)
    }

    suspend fun getAll() = ApplicationController.instance?.appDatabase?.userDAO?.getAll() ?: listOf()
}