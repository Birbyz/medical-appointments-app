package com.example.medicalappointments.data.repositories

import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.models.UserModel

object UserRepository {
    suspend fun insert(entities: List<UserModel>) {
        // creates a new thread
        // allows getting methods from DAO without blocking the main thread
        ApplicationController.instance?.appDatabase?.userDAO?.insert(entities)
    }

    suspend fun getAllUsers() = ApplicationController.instance?.appDatabase?.userDAO?.getAll() ?: listOf()
}