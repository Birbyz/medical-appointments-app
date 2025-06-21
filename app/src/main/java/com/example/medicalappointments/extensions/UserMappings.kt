package com.example.medicalappointments.extensions

import com.example.medicalappointments.models.User
import com.example.medicalappointments.data.models.UserEntityModel

fun UserEntityModel.toModel(): User = User(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar
)

fun User.toEntity(): UserEntityModel = UserEntityModel(
    id = id,
    email = email,
    firstName = firstName,
    lastName = lastName,
    avatar = avatar
)