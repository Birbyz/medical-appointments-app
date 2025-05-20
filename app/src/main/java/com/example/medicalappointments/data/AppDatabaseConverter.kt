package com.example.medicalappointments.data

import androidx.room.TypeConverter
import com.example.medicalappointments.models.CategoryType
import java.time.LocalDate

class AppDatabaseConverter {
    // TRANSFORM A DATA TYPE INTO A DATA STRUCTURE
    @TypeConverter
    fun categoryTypeToInt(categoryType: CategoryType): Int = categoryType.id

    @TypeConverter
    fun intToCategoryType(id: Int): CategoryType = CategoryType.getCategoryTypeById(id)

//  LOCAL DATE CONVERTERS
    @TypeConverter
    fun fromStringToDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }

    @TypeConverter
    fun fromDateToString(date: LocalDate?): String? = date?.toString()
}