package com.example.medicalappointments.data.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.medicalappointments.models.CategoryType

@Entity
class CategoryEntityModel (
    @PrimaryKey
    @ColumnInfo(name = ID)
    val id: Long = 0,
    val category: CategoryType
){
    companion object{
        const val ID = "id"
    }
}