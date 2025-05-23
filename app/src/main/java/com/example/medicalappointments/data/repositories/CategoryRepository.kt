package com.example.medicalappointments.data.repositories

import com.example.medicalappointments.ApplicationController
import com.example.medicalappointments.data.models.CategoryEntityModel
import com.example.medicalappointments.data.models.CategoryWithAppointmentsEntityModel
import com.example.medicalappointments.utils.extensions.logErrorMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object CategoryRepository {
    suspend fun insert(entityModel: CategoryEntityModel) {
        // creates a new thread
        // allows getting methods from DAO without blocking the main thread
        ApplicationController.instance?.appDatabase?.categoryDAO?.insert(entityModel)
    }

    suspend fun getAllCategoriesWithAppointments(): List<CategoryWithAppointmentsEntityModel> {
        return ApplicationController.instance?.appDatabase?.categoryDAO?.getCategoriesWithAppointments() ?: listOf()
    }
}