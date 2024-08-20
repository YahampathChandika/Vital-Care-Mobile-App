package com.example.vitalcare.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vitalcare.R

@Composable
fun AddPatientScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Add Patient",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        // Here you can add your form fields for adding a patient.

        Button(
            onClick = {
                // Logic to save the patient or go back
                navController.popBackStack() // Navigate back to the dashboard
            },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text(text = "Save")
        }
    }
}
