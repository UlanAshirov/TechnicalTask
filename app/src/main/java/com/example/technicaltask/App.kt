package com.example.technicaltask

import android.app.Application
import android.content.SharedPreferences
import com.example.technicaltask.utils.Prefs

class App : Application() {
    private lateinit var preferences: SharedPreferences

    companion object {
        lateinit var prefs: Prefs
    }

    override fun onCreate() {
        super.onCreate()
        preferences = this.applicationContext
            .getSharedPreferences("setting", MODE_PRIVATE)
        prefs = Prefs(preferences)
    }
}