package com.example.technicaltask.utils

import android.content.SharedPreferences

class Prefs(private val preferences: SharedPreferences) {

    fun saveId(id: String) {
        preferences.edit().putString("id", id).apply()
    }

    fun getId(): String? {
        return preferences.getString("id", "")
    }

    fun saveUuid(uuid: String) {
        preferences.edit().putString("uuid", uuid).apply()
    }

    fun getUuid(): String? {
        return preferences.getString("uuid", "")
    }

}