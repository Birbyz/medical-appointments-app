package com.example.medicalappointments.data.dao


import com.example.medicalappointments.data.models.Doctor
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medicalappointments.models.DoctorModel

@Dao
interface DoctorDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDoctor(doctor: Doctor): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDoctors(doctors: List<Doctor>): List<Long>

    // GET ALL
    @Query("SELECT * FROM doctors")
    suspend fun getAll(): List<Doctor>
}