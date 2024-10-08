package com.example.vitalcare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
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
import com.example.vitalcare.data.Patient
import com.example.vitalcare.data.User
import com.example.vitalcare.navigation.NavRoutes
import com.example.vitalcare.util.JsonUtil
import com.google.gson.Gson

@Composable
fun DashboardScreen(user: User, navController: NavController) {
    // Load patient data
    val context = LocalContext.current
    val patients = remember { JsonUtil.loadPatients(context) }

    val scrollState = rememberScrollState()
    val searchQuery = remember { mutableStateOf("") }
    val filteredPatients = patients.filter { patient ->
        patient.name.contains(searchQuery.value, ignoreCase = true)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp)
            .verticalScroll(scrollState),

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .width(100.dp)
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = "Profile Picture",
                    modifier = Modifier
                        .size(35.dp)
                        .clip(CircleShape)
                )

                Column(
                    modifier = Modifier.padding(start = 5.dp)
                ) {
                    Text(
                        text = user.name,
                        color = colorResource(id = R.color.black),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.W600,
                    )

                    Text(
                        text = user.role,
                        color = colorResource(id = R.color.dgray),
                        fontSize = 11.sp,
                        lineHeight = 11.sp
                    )
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 40.dp)
        ) {
            Text(
                text = "Good Morning, ${user.name.split(" ")[0]}!",
                color = colorResource(id = R.color.lblack),
                fontSize = 22.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "Check the latest updates on your account.",
                color = colorResource(id = R.color.dgray),
                fontSize = 14.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically,


        ) {
            Column(
                modifier = Modifier
                    .weight(0.1f)
                    .shadow(
                        elevation = 4.dp, // Adjusted shadow size for visibility
                        shape = RoundedCornerShape(12.dp), // Same radius as border for consistency
                        clip = false
                    )
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp)) // Clip to match the border shape
                    .background(color = colorResource(id = R.color.white))
                    .padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    )
            )
            {
                Text(
                    text = "Patients",
                    color = colorResource(id = R.color.lblack),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500,
                )
                Text(
                    text = "Current",
                    color = colorResource(id = R.color.dgray),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = "0${patients.size.toString()}",
                    color = colorResource(id = R.color.lblue),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.1f)
                    .shadow(
                        elevation = 4.dp, // Adjusted shadow size for visibility
                        shape = RoundedCornerShape(12.dp), // Same radius as border for consistency
                        clip = false
                    )
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp)) // Clip to match the border shape
                    .background(color = colorResource(id = R.color.white))
                    .padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    )
            ) {
                Text(
                    text = "Alerts",
                    color = colorResource(id = R.color.lblack),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500,
                )
                Text(
                    text = "Current",
                    color = colorResource(id = R.color.dgray),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = "0${patients.sumOf { it.alerts }.toString()}",
                    color = colorResource(id = R.color.lblue),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(20.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(0.1f)
                    .shadow(
                        elevation = 4.dp, // Adjusted shadow size for visibility
                        shape = RoundedCornerShape(12.dp), // Same radius as border for consistency
                        clip = false
                    )
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp)) // Clip to match the border shape
                    .background(color = colorResource(id = R.color.white))
                    .padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    )
            ) {
                Text(
                    text = "Patients In",
                    color = colorResource(id = R.color.lblack),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500,
                )
                Text(
                    text = "Today",
                    color = colorResource(id = R.color.dgray),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = "04",
                    color = colorResource(id = R.color.lblue),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )
            }

            Column(
                modifier = Modifier
                    .weight(0.1f)
                    .shadow(
                        elevation = 4.dp, // Adjusted shadow size for visibility
                        shape = RoundedCornerShape(12.dp), // Same radius as border for consistency
                        clip = false
                    )
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp)) // Clip to match the border shape
                    .background(color = colorResource(id = R.color.white))
                    .padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    )
            ) {
                Text(
                    text = "Patients Out",
                    color = colorResource(id = R.color.lblack),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.W500,
                )
                Text(
                    text = "Today",
                    color = colorResource(id = R.color.dgray),
                    fontSize = 12.sp,
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                Text(
                    text = "03",
                    color = colorResource(id = R.color.lblue),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }

        @OptIn(ExperimentalMaterial3Api::class)
        OutlinedTextField(
            value = searchQuery.value,
            onValueChange = { searchQuery.value = it },
            label = { Text("Search Patients") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            shape = RoundedCornerShape(12.dp),
            textStyle = TextStyle(color = colorResource(id = R.color.lblack)),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = colorResource(id = R.color.lgray),
                    modifier = Modifier.size(30.dp)
                )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = colorResource(id = R.color.lgray),  // Border color when focused
                unfocusedBorderColor = colorResource(id = R.color.lgray)  // Border color when unfocused
            )
        )


        // Patient list section
        Column(modifier = Modifier.padding(top = 20.dp)) {
            // Patient list section with "Add Patient" button
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Patient Details",
                    color = colorResource(id = R.color.lblack),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.W700,
                    modifier = Modifier.padding(vertical = 10.dp)
                )

                Button(
                    onClick = {
                        navController.navigate(NavRoutes.AddPatient)
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = colorResource(id = R.color.lblue),
                        contentColor = Color.White
                    ),
                    modifier = Modifier.padding(vertical = 10.dp)
                ) {
                    Text(text = "Add Patient")
                }

            }

            // Display patient list
            filteredPatients.forEach { patient ->
                PatientCard(patient = patient) { selectedPatient ->
                    // Navigate to patient detail screen
                    val jsonPatient = Gson().toJson(selectedPatient)
                    navController.navigate("${NavRoutes.PatientDetail}/$jsonPatient")
                }
            }
        }

    }
}

@Composable
fun PatientCard(patient: Patient, onClick: (Patient) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
            .clickable { onClick(patient) },  // Make the card clickable
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
            Text(text =
                patient.name,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                color = colorResource(id = R.color.lblack)
            )
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
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.dgray)
                        )
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
                            fontSize = 18.sp,
                            color = colorResource(id = R.color.dgray)
                        )
                    }
                }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardScreenPreview() {
    val mockUser = User(
        username = "admin",
        password = "1234",
        name = "Dr. Jane Doe",
        role = "Chief Surgeon"
    )

    // Since NavController cannot be used directly in previews, we use a mock or placeholder
    val mockNavController = rememberNavController()

    // DashboardScreen is previewed with mock user and mock navigation controller
    DashboardScreen(user = mockUser, navController = mockNavController)
}
