package com.example.vitalcare.util

import android.content.Context
import android.util.Log
import com.example.vitalcare.data.Patient
import com.example.vitalcare.data.User
import com.example.vitalcare.data.VitalSign
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File
import java.io.InputStreamReader

object JsonUtil {
    private const val TAG = "JsonUtil"

    fun loadUsers(context: Context): List<User> {
        val inputStream = context.assets.open("users.json")
        val reader = InputStreamReader(inputStream)

        // Directly parse the JSON array of users
        val userType = object : TypeToken<List<User>>() {}.type
        val users: List<User> = Gson().fromJson(reader, userType)

        // Debug: Log loaded users
        Log.d(TAG, "Loaded users: $users")

        return users
    }

    fun loadPatients(context: Context): List<Patient> {
        val file = File(context.filesDir, "patients.json")

        if (!file.exists()) {
            Log.e(TAG, "File not found: ${file.absolutePath}")
            return emptyList()
        }

        val json = file.readText()
        Log.d(TAG, "File content: $json")

        val patientType = object : TypeToken<List<Patient>>() {}.type
        val patients: List<Patient> = Gson().fromJson(json, patientType)

        // Debug: Log loaded patients
        Log.d(TAG, "Loaded patients: $patients")

        return patients
    }

    fun savePatients(context: Context, patients: List<Patient>) {
        val file = File(context.filesDir, "patients.json")
        file.writeText(Gson().toJson(patients))

        // Debug: Log saved patients
        Log.d(TAG, "Saved patients: $patients")
    }

    fun saveVitalSigns(context: Context, patientId: String, vitalSign: VitalSign) {
        val file = File(context.filesDir, "${patientId}_vitals.json")
        val existingVitals = if (file.exists()) {
            val json = file.readText()
            Gson().fromJson(json, Array<VitalSign>::class.java).toMutableList()
        } else {
            mutableListOf()
        }
        existingVitals.add(vitalSign)
        file.writeText(Gson().toJson(existingVitals))
    }

    fun loadVitalSigns(context: Context, patientId: String): List<VitalSign> {
        val file = File(context.filesDir, "${patientId}_vitals.json")

        if (!file.exists()) {
            Log.e(TAG, "Vital signs file not found for patientId: $patientId")
            return emptyList()
        }

        val json = file.readText()
        val vitalSignType = object : TypeToken<List<VitalSign>>() {}.type
        return Gson().fromJson(json, vitalSignType)
    }


}