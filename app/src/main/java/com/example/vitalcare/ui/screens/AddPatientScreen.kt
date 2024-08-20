package com.example.vitalcare.ui.screens

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun AddPatientScreen(navController: NavController) {
    // Your UI for adding a patient goes here

    // Example Back Button
    Button(onClick = { navController.popBackStack() }) {
        Text(text = "Back to Dashboard")
    }
}
