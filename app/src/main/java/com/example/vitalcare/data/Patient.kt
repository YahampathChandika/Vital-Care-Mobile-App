package com.example.vitalcare.data

data class Patient(
    val id: String,
    val name: String,
    val age: Int,
    val gender: String,
    val diagnosis: String,
    val alerts: Int,
    val bed: Int,
    val status: String
)
