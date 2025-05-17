package com.example.medicalappointments.models

import com.example.medicalappointments.R

sealed class Category (
    open val id: Int,
    val name: String,
    val type: CategoryType
)

class Surgery(id: Int): Category(id, "Surgery", CategoryType.SURGERY)
class Video(id: Int): Category(id, "Video", CategoryType.VIDEO)
class Regular(id: Int): Category(id, "Regular", CategoryType.REGULAR)
class Follow_Up(id: Int): Category(id, "Follow up", CategoryType.FOLLOW_UP)

enum class CategoryType(val id: Int, val resourceId: Int) {
    SURGERY(0, R.string.surgery),
    VIDEO(1, R.string.video_conference),
    REGULAR(2, R.string.regular),
    FOLLOW_UP(3, R.string.follow_up)
}