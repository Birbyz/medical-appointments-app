package com.example.medicalappointments.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medicalappointments.data.models.CategoryEntityModel
import com.example.medicalappointments.data.models.CategoryWithAppointmentsEntityModel

@Dao
interface CategoryDAO {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: CategoryEntityModel)

    @Query("""
        SELECT * FROM CategoryEntityModel
    """)
    suspend fun getCategoriesWithAppointments(): List<CategoryWithAppointmentsEntityModel>
}