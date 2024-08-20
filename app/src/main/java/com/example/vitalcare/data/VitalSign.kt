package com.example.vitalcare.data

data class VitalSign(
    val date: String,
    val time: String,
    val temperature: Float,
    val heartRate: Int,
    val bloodPressure: String,
    val respiratoryRate: Int
)
