package com.example.medicalappointments.data.models

import android.content.Context
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntityModel (
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String
) {
    companion object {
        fun defaultCategorySeed(context: Context): List<CategoryEntityModel> {
            return com.example.medicalappointments.models.Category.entries
                .filter { it != com.example.medicalappointments.models.Category.UNKNOWN }
                .map {
                    CategoryEntityModel(
                        id = it.id,
                        name = context.getString(it.nameRes)
                    )
                }
        }
    }
}