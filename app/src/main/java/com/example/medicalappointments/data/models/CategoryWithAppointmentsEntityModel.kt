package com.example.medicalappointments.data.models

import androidx.room.Embedded
import androidx.room.Relation

data class CategoryWithAppointmentsEntityModel (
    @Embedded
    val category: CategoryEntityModel,

    // ONE TO MANY
    @Relation(
        parentColumn = CategoryEntityModel.ID,
        entityColumn = AppointmentEntityModel.CATEGORY_ID
    )

    val appointmentsList: List<AppointmentEntityModel>
)