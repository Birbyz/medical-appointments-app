package com.example.medicalappointments.data

import androidx.room.TypeConverter
import com.example.medicalappointments.models.Category
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AppDatabaseConverter {
    private val dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME

    // TRANSFORM A DATA TYPE INTO A DATA STRUCTURE
    @TypeConverter
    fun categoryToInt(category: Category): Int = category.id

    @TypeConverter
    fun intToCategory(id: Int): Category = Category.fromId(id)

//  LOCAL DATE CONVERTERS
    @TypeConverter
    fun fromStringToDate(value: String?): LocalDate? = value?.let { LocalDate.parse(it) }

    @TypeConverter
    fun fromDateToString(date: LocalDate?): String? = date?.toString()

    // LOCAL DATETIME
    @TypeConverter
    fun fromDateTime(dateTime: LocalDateTime?): String? = dateTime?.format(dateTimeFormatter)

    @TypeConverter
    fun toDateTime(value: String?): LocalDateTime? = value?.let { LocalDateTime.parse(it, dateTimeFormatter) }
}