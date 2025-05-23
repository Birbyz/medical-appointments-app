package com.example.medicalappointments.models

import android.content.Context
import com.example.medicalappointments.R

enum class Category(
    val id: Int,
    val nameRes: Int,      // referință la string din res/values/strings.xml
    val iconRes: String       // referință la drawable din res/drawable
) {
    SURGERY(
        id = 0,
        nameRes = R.string.surgery,
        iconRes = "https://avatar.iran.liara.run/public"
    ),
    VIDEO(
        id = 1,
        nameRes = R.string.video_conference,
        iconRes = "https://avatar.iran.liara.run/public"
    ),
    REGULAR(
        id = 2,
        nameRes = R.string.regular,
        iconRes = "https://avatar.iran.liara.run/public"
    ),
    FOLLOW_UP(
        id = 3,
        nameRes = R.string.follow_up,
        iconRes = "https://avatar.iran.liara.run/public"
    ),
    EMERGENCY(
      id = 4,
        nameRes = R.string.emergency,
        iconRes = "https://avatar.iran.liara.run/public"
    ),
    UNKNOWN(
        id = -1,
        nameRes = R.string.unknown,
        iconRes = "https://avatar.iran.liara.run/public"
    );

    // Returnează numele localizat
    fun getDisplayName(context: Context): String = context.getString(nameRes)

    companion object {
        fun fromId(id: Int): Category = entries.find { it.id == id } ?: UNKNOWN
    }
}
