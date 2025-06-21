package com.example.medicalappointments.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.medicalappointments.models.Specialty

@Entity(tableName = "specialties")
data class SpecialtyEntityModel (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
) {
    companion object {
        fun defaultSpecialtySeed(): List<SpecialtyEntityModel> = listOf(
            SpecialtyEntityModel(name = "Neurologist"),
            SpecialtyEntityModel(name = "Ophthalmologist"),
            SpecialtyEntityModel(name = "Cardiologist"),
            SpecialtyEntityModel(name = "Pediatrician"),
            SpecialtyEntityModel(name = "Dermatologist"),
            SpecialtyEntityModel(name = "Psychiatrist"),
            SpecialtyEntityModel(name = "Radiologist"),
            SpecialtyEntityModel(name = "Oncologist"),
            SpecialtyEntityModel(name = "Endocrinologist"),
            SpecialtyEntityModel(name = "General Practitioner"),
            SpecialtyEntityModel(name = "Orthopedic Surgeon"),
            SpecialtyEntityModel(name = "Pulmonologist"),
            SpecialtyEntityModel(name = "Gastroenterologist"),
            SpecialtyEntityModel(name = "Nephrologist")
        )
    }
}