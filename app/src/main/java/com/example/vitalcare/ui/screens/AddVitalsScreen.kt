package com.example.vitalcare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vitalcare.R
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
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(24.dp)
            .imePadding(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
//                .padding(vertical = 30.dp),
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
            text = "Add Vital Signs",
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            color = colorResource(id = R.color.dblue),
            modifier = Modifier.padding(vertical = 24.dp)
        )

        OutlinedTextField(value = date, onValueChange = { date = it }, label = { Text("Date") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), textStyle = TextStyle(color = colorResource(id = R.color.lblack)))
        OutlinedTextField(value = time, onValueChange = { time = it }, label = { Text("Time") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), textStyle = TextStyle(color = colorResource(id = R.color.lblack)))
        OutlinedTextField(value = temperature, onValueChange = { temperature = it }, label = { Text("Temperature") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), textStyle = TextStyle(color = colorResource(id = R.color.lblack)))
        OutlinedTextField(value = heartRate, onValueChange = { heartRate = it }, label = { Text("Heart Rate") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), textStyle = TextStyle(color = colorResource(id = R.color.lblack)))
        OutlinedTextField(value = bloodPressure, onValueChange = { bloodPressure = it }, label = { Text("Blood Pressure") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), textStyle = TextStyle(color = colorResource(id = R.color.lblack)))
        OutlinedTextField(value = respiratoryRate, onValueChange = { respiratoryRate = it }, label = { Text("Respiratory Rate") }, modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp), textStyle = TextStyle(color = colorResource(id = R.color.lblack)))

        Button(onClick = {
            val newVitals = VitalSign(date, time, temperature.toFloat(), heartRate.toInt(), bloodPressure, respiratoryRate.toInt())
            JsonUtil.saveVitalSigns(context, patientId, newVitals)
            navController.navigateUp()  // Go back to PatientDetailScreen
        },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.lblue),
                contentColor = Color.White
            ),
            modifier = Modifier
                .padding(top = 16.dp)
                .width(250.dp)
        ) {
            Text(text = "Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddVitalsScreenPreview() {
    val mockNavController = rememberNavController()
    AddVitalsScreen(patientId = "mockPatientId", navController = mockNavController)
}
