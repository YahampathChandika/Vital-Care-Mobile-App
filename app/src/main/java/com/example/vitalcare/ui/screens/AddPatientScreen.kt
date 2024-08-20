package com.example.vitalcare.ui.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vitalcare.R
import com.example.vitalcare.data.Patient
import com.example.vitalcare.util.JsonUtil
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.File

@Composable
fun AddPatientScreen(navController: NavController, context: Context) {
    // State variables for form inputs
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var diagnosis by remember { mutableStateOf("") }
    var alerts by remember { mutableStateOf("") }
    var bed by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    // Error state variables
    var nameError by remember { mutableStateOf<String?>(null) }
    var ageError by remember { mutableStateOf<String?>(null) }
    var genderError by remember { mutableStateOf<String?>(null) }
    var diagnosisError by remember { mutableStateOf<String?>(null) }
    var alertsError by remember { mutableStateOf<String?>(null) }
    var bedError by remember { mutableStateOf<String?>(null) }
    var statusError by remember { mutableStateOf<String?>(null) }

    // Function to validate form inputs
    fun validateInputs(): Boolean {
        var valid = true

        if (name.isBlank()) {
            nameError = "Name is required"
            valid = false
        } else {
            nameError = null
        }

        if (age.isBlank() || age.toIntOrNull() == null) {
            ageError = "Valid age is required"
            valid = false
        } else {
            ageError = null
        }

        if (gender.isBlank()) {
            genderError = "Gender is required"
            valid = false
        } else {
            genderError = null
        }

        if (diagnosis.isBlank()) {
            diagnosisError = "Diagnosis is required"
            valid = false
        } else {
            diagnosisError = null
        }

        if (alerts.isBlank() || alerts.toIntOrNull() == null) {
            alertsError = "Valid alerts number is required"
            valid = false
        } else {
            alertsError = null
        }

        if (bed.isBlank() || bed.toIntOrNull() == null) {
            bedError = "Valid bed number is required"
            valid = false
        } else {
            bedError = null
        }

        if (status.isBlank()) {
            statusError = "Status is required"
            valid = false
        } else {
            statusError = null
        }

        return valid
    }

    // Function to save a patient to the JSON file
    fun savePatient() {
        val newPatient = Patient(
            id = (JsonUtil.loadPatients(context).size + 1).toString(), // Generate new ID
            name = name,
            age = age.toInt(),
            gender = gender,
            diagnosis = diagnosis,
            alerts = alerts.toInt(),
            bed = bed.toInt(),
            status = status
        )
        val patients = JsonUtil.loadPatients(context).toMutableList()
        patients.add(newPatient)
        JsonUtil.savePatients(context, patients)
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 30.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(120.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                )
            }
        }
        Text(
            text = "Add Patient",
            fontSize = 24.sp,
            fontWeight = FontWeight.W500,
            color = colorResource(id = R.color.dblue),
             modifier = Modifier.padding(bottom = 24.dp))

        // Form fields with basic styling
        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            isError = nameError != null,
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (nameError != null) {
            Text(text = nameError!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = age,
            onValueChange = { age = it },
            label = { Text("Age") },
            isError = ageError != null,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (ageError != null) {
            Text(text = ageError!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = gender,
            onValueChange = { gender = it },
            label = { Text("Gender") },
            isError = genderError != null,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (genderError != null) {
            Text(text = genderError!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = diagnosis,
            onValueChange = { diagnosis = it },
            label = { Text("Diagnosis") },
            isError = diagnosisError != null,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (diagnosisError != null) {
            Text(text = diagnosisError!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = alerts,
            onValueChange = { alerts = it },
            label = { Text("Alerts") },
            isError = alertsError != null,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (alertsError != null) {
            Text(text = alertsError!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = bed,
            onValueChange = { bed = it },
            label = { Text("Bed") },
            isError = bedError != null,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (bedError != null) {
            Text(text = bedError!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Status") },
            isError = statusError != null,
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        if (statusError != null) {
            Text(text = statusError!!, color = MaterialTheme.colorScheme.error, fontSize = 12.sp)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (validateInputs()) {
                    savePatient()
                    navController.popBackStack() // Navigate back to the dashboard
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.lblue),
                contentColor = Color.White
            ),
            modifier = Modifier.padding(top = 16.dp)
                .width(250.dp)
        ) {
            Text(text = "Save")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun AddPatientScreenPreview() {
    // Use a mock NavController for preview purposes
    val mockNavController = rememberNavController()
    // Context is usually not available in previews, but you can pass a dummy context or use a LocalContext
    val context = LocalContext.current

    AddPatientScreen(navController = mockNavController, context = context)
}