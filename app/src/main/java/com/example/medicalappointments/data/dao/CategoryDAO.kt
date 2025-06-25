package com.example.medicalappointments.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medicalappointments.data.models.CategoryEntityModel

@Dao
interface CategoryDAO {
    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(category: CategoryEntityModel)

    @Insert (onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(category: List<CategoryEntityModel>)

    @Query("SELECT * FROM categories")
    suspend fun getAll(): List<CategoryEntityModel>
}