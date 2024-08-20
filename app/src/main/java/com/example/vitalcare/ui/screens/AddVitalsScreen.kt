package com.example.vitalcare.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vitalcare.data.VitalSign
import com.example.vitalcare.util.JsonUtil

@Composable
fun AddVitalsScreen(patientId: String, navController: NavController) {
    val context = LocalContext.current
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    var temperature by remember { mutableStateOf("") }
    var heartRate by remember { mutableStateOf("") }
    var bloodPressure by remember { mutableStateOf("") }
    var respiratoryRate by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(16.dp)
        .imePadding(),
    ) {
        Text(text = "Add Vital Signs", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))

        OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
        OutlinedTextField(value = time, onValueChange = { time = it }, label = { Text("Time") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
        OutlinedTextField(value = temperature, onValueChange = { temperature = it }, label = { Text("Temperature") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
        OutlinedTextField(value = heartRate, onValueChange = { heartRate = it }, label = { Text("Heart Rate") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
        OutlinedTextField(value = bloodPressure, onValueChange = { bloodPressure = it }, label = { Text("Blood Pressure") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))
        OutlinedTextField(value = respiratoryRate, onValueChange = { respiratoryRate = it }, label = { Text("Respiratory Rate") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp))

        Button(onClick = {
            val newVitals = VitalSign(date, time, temperature.toFloat(), heartRate.toInt(), bloodPressure, respiratoryRate.toInt())
            JsonUtil.saveVitalSigns(context, patientId, newVitals)
            navController.navigateUp()  // Go back to PatientDetailScreen
        }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Save Vitals")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddVitalsScreenPreview() {
    val mockNavController = rememberNavController()
    AddVitalsScreen(patientId = "mockPatientId", navController = mockNavController)
}
