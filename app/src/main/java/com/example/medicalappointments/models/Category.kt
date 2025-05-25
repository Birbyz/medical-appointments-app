package com.example.medicalappointments.models

import android.content.Context
import com.example.medicalappointments.R

enum class Category(
    val id: Int,
    val nameRes: Int,      // referință la string din res/values/strings.xml
    val iconRes: Int       // referință la drawable din res/drawable
) {
    SURGERY(
        id = 0,
        nameRes = R.string.surgery,
        iconRes = R.drawable.ic_surgery_appointment
    ),
    VIDEO(
        id = 1,
        nameRes = R.string.video_conference,
        iconRes = R.drawable.ic_video_appointment
    ),
    REGULAR(
        id = 2,
        nameRes = R.string.regular,
        iconRes = R.drawable.ic_regular_appointment
    ),
    FOLLOW_UP(
        id = 3,
        nameRes = R.string.follow_up,
        iconRes = R.drawable.ic_follow_up_appointment
    ),
    EMERGENCY(
      id = 4,
        nameRes = R.string.emergency,
        iconRes = R.drawable.ic_emergency_appointment
    ),
    UNKNOWN(
        id = -1,
        nameRes = R.string.unknown,
        iconRes = R.drawable.ic_unknown
    );

    // Returnează numele localizat
    fun getDisplayName(context: Context): String = context.getString(nameRes)

    companion object {
        fun fromId(id: Int): Category = entries.find { it.id == id } ?: UNKNOWN
    }
}
