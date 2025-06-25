package com.example.medicalappointments.managers

import com.example.medicalappointments.ApplicationController

object SharedPrefsManager {

    private const val KEY_AUTH_TOKEN = "auth_token"

    fun saveAuthToken(token: String) = sharedPrefs?.let { sp ->
        with(sp.edit()) {
            putString(KEY_AUTH_TOKEN, token)
            apply()
        }
    }

    fun clearAuthToken() {
        sharedPrefs?.edit()?.remove(KEY_AUTH_TOKEN)?.apply()
    }

    fun getAuthToken(): String? = sharedPrefs?.getString(KEY_AUTH_TOKEN, null)

    private val sharedPrefs
        get() = ApplicationController.instance?.sharedPrefs

    fun getUserIdFromToken(): Long? {
        val token = getAuthToken() ?: return null

        return token.split("_").getOrNull(1)?.toLongOrNull()
    }
}