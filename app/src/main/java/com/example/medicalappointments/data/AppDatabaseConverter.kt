package com.example.medicalappointments.data

import androidx.room.TypeConverter
import com.example.medicalappointments.models.CategoryType

class AppDatabaseConverter {
    // TRANSFORM A DATA TYPE INTO A DATA STRUCTURE
    @TypeConverter
    fun categoryTypeToInt(categoryType: CategoryType): Int = categoryType.id

    @TypeConverter
    fun intToCategoryType(id: Int): CategoryType = CategoryType.getCategoryTypeById(id)
}