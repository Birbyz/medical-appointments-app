package com.example.medicalappointments.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medicalappointments.data.models.DoctorEntityModel

@Dao
interface DoctorDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDoctor(doctor: DoctorEntityModel): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDoctors(doctors: List<DoctorEntityModel>): List<Long>

    // GET ALL
    @Query("SELECT * FROM doctors")
    suspend fun getAll(): List<DoctorEntityModel>

    @Query("SELECT * FROM doctors WHERE specialtyId = :specialtyId")
    suspend fun getDoctorsBySpecialtyId(specialtyId: Long): List<DoctorEntityModel>
}