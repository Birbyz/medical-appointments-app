package com.example.medicalappointments.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medicalappointments.data.models.SpecialtyEntityModel

@Dao
interface SpecialtyDAO {
    @Query("SELECT * FROM specialties")
    suspend fun getAll(): List<SpecialtyEntityModel>

    @Query("SELECT name FROM specialties")
    suspend fun getNames(): List<String>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(specialties: List<SpecialtyEntityModel>)
}