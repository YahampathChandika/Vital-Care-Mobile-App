package com.example.vitalcare.util

import android.content.Context
import com.example.vitalcare.data.Patient
import com.example.vitalcare.data.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

object JsonUtil {
    fun loadUsers(context: Context): List<User> {
        val inputStream = context.assets.open("users.json")
        val reader = InputStreamReader(inputStream)

        // Directly parse the JSON array of users
        val userType = object : TypeToken<List<User>>() {}.type
        return Gson().fromJson(reader, userType)
    }

    fun loadPatients(context: Context): List<Patient> {
        val json = context.assets.open("patients.json").bufferedReader().use { it.readText() }
        val patientType = object : TypeToken<List<Patient>>() {}.type
        return Gson().fromJson(json, patientType)
    }
}
