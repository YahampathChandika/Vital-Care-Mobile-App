package com.example.vitalcare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.vitalcare.R
import com.example.vitalcare.data.Patient
import com.example.vitalcare.navigation.NavRoutes
import androidx.compose.ui.platform.LocalContext
import com.example.vitalcare.data.VitalSign
import com.example.vitalcare.util.JsonUtil

@Composable
fun PatientDetailScreen(patient: Patient, navController: NavController) {

    val context = LocalContext.current
    val vitalSigns = remember {
        JsonUtil.loadVitalSigns(context, patient.id)
    }
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
        ) {
        // Patient Header
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
            text = "Patient Details",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Patient Details
        Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
    ) {
        // Existing content
        Column(
            modifier = Modifier
                .border(
                    width = 2.dp,
                    color = colorResource(id = R.color.white),
                    shape = RoundedCornerShape(12.dp)
                )
                .clip(RoundedCornerShape(12.dp)) // Clip to match the border shape
                .background(color = colorResource(id = R.color.white))
                .padding(
                    horizontal = 20.dp,
                    vertical = 15.dp
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Text( text = patient.status,
                        fontWeight = FontWeight.Bold,
                        color = when (patient.status) {
                            "Critical" -> colorResource(id = R.color.red)
                            "Stable" -> colorResource(id = R.color.green)
                            "Unstable" -> colorResource(id = R.color.yellow)
                            else -> Color.Black
                        })
                    Text(text = "Alerts | 0${patient.alerts}",
                        color = colorResource(id = R.color.lblue),
                    )
            }
            Text(text = patient.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ){
                    Column {
                        Text(
                            text = "Bed No",
                            color = colorResource(id = R.color.lgray),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Text(
                            text = "0${patient.bed}",
                            fontSize = 18.sp)
                    }
                    Column(
                        Modifier.width(80.dp),
                        ) {
                        Text(
                            text = "Diagnosis",
                            color = colorResource(id = R.color.lgray),
                            modifier = Modifier.padding(top = 10.dp)
                        )
                        Text(
                            text = patient.diagnosis,
                            fontSize = 18.sp)
                    }
                }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ){
                Column {
                    Text(
                        text = "Age",
                        color = colorResource(id = R.color.lgray),
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = patient.age.toString(),
                        fontSize = 18.sp)
                }
                Column(
                    Modifier.width(80.dp)
                ) {
                    Text(
                        text = "Gender",
                        color = colorResource(id = R.color.lgray),
                        modifier = Modifier.padding(top = 10.dp)
                    )
                    Text(
                        text = patient.gender,
                        fontSize = 18.sp)
                }

            }
        }
    }

        // Add Vitals Button
        Button(
            onClick = {
                navController.navigate("${NavRoutes.AddVitals}/${patient.id}")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Blue, contentColor = Color.White)
        ) {
            Text(text = "Add Vitals")
        }

        // Vital Signs List
        if (vitalSigns.isNotEmpty()) {
            Text(
                text = "Vital Signs",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )
            Column {
                vitalSigns.forEach { vitalSign ->
                    VitalSignCard(vitalSign = vitalSign)
                }
            }
        } else {
            Text(
                text = "No vital signs recorded.",
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 16.dp)
            )
        }
    }
}

@Composable
fun VitalSignCard(vitalSign: VitalSign) {
    Card(modifier = Modifier.padding(bottom = 8.dp)) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Date: ${vitalSign.date}", fontWeight = FontWeight.Bold)
            Text("Time: ${vitalSign.time}")
            Text("Temperature: ${vitalSign.temperature}°C")
            Text("Heart Rate: ${vitalSign.heartRate} bpm")
            Text("Blood Pressure: ${vitalSign.bloodPressure}")
            Text("Respiratory Rate: ${vitalSign.respiratoryRate} bpm")
        }
    }
}

// Helper function to get color based on patient status
@Composable
fun getStatusColor(status: String): Color {
    return when (status) {
        "Critical" -> Color.Red
        "Stable" -> Color.Green
        "Unstable" -> Color.Yellow
        else -> Color.Gray
    }
}

@Preview(showBackground = true)
@Composable
fun PatientDetailScreenPreview() {
    val mockPatient = Patient(
        id = "1",
        name = "John Doe",
        age = 45,
        gender = "Male",
        bed = 12,
        diagnosis = "Flu",
        status = "Stable",
        alerts = 1
    )
    val mockNavController = rememberNavController()

    PatientDetailScreen(patient = mockPatient, navController = mockNavController)
}
