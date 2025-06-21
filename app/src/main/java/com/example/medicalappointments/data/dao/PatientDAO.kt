package com.example.medicalappointments.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.medicalappointments.models.Patient
import com.example.medicalappointments.models.PatientEntityModel

@Dao
interface PatientDAO {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(patients: PatientEntityModel)

    //INSERT ONE PATIENT
//    @Insert(onConflict = OnConflictStrategy.IGNORE)
//    suspend fun getAllPatients(): List<Patient>

    // GET ALL PATIENTS
    @Query("SELECT * FROM patients")
    suspend fun getAll(): List<PatientEntityModel>
}