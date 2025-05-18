package com.example.medicalappointments.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppointmentEntityModel (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,

    @ColumnInfo(name = CATEGORY_ID) // used later for reference to avoid hard-coding
    val categoryId: Long // FK one-to-many
){
    companion object{
        const val CATEGORY_ID = "categoryId"
    }
}