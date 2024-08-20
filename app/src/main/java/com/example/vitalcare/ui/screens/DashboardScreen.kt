package com.example.vitalcare.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.vitalcare.R
import com.example.vitalcare.data.Patient
import com.example.vitalcare.data.User
import com.example.vitalcare.navigation.NavRoutes
import com.example.vitalcare.util.JsonUtil

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

                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = user.name,
                        color = colorResource(id = R.color.lblack),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W500,
                    )

                    Text(
                        text = user.role,
                        color = colorResource(id = R.color.dgray),
                        fontSize = 15.sp,
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
                fontSize = 24.sp,
                fontWeight = FontWeight.W500,
                modifier = Modifier.padding(bottom = 12.dp)
            )

            Text(
                text = "Check the latest updates on your account.",
                color = colorResource(id = R.color.dgray),
                fontSize = 15.sp,
                modifier = Modifier.padding(bottom = 12.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp)) // Clip to match the border shape
                    .background(color = colorResource(id = R.color.white))
//                    .shadow(
//                        elevation = 8.dp, // Shadow size
//                        shape = RoundedCornerShape(12.dp), // Same radius as border for consistency
//                        clip = false
//                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    )
                    .width(130.dp)
            ) {
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
                    text = "10",
                    color = colorResource(id = R.color.lblue),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )
            }

            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp)) // Clip to match the border shape
                    .background(color = colorResource(id = R.color.white))
//                    .shadow(
//                        elevation = 8.dp, // Shadow size
//                        shape = RoundedCornerShape(12.dp), // Same radius as border for consistency
//                        clip = false
//                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    )
                    .width(130.dp)
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
                    text = "08",
                    color = colorResource(id = R.color.lblue),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W500
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp)) // Clip to match the border shape
                    .background(color = colorResource(id = R.color.white))
//                    .shadow(
//                        elevation = 8.dp, // Shadow size
//                        shape = RoundedCornerShape(12.dp), // Same radius as border for consistency
//                        clip = false
//                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    )
                    .width(130.dp)
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
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.white),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clip(RoundedCornerShape(12.dp)) // Clip to match the border shape
                    .background(color = colorResource(id = R.color.white))
//                    .shadow(
//                        elevation = 8.dp, // Shadow size
//                        shape = RoundedCornerShape(12.dp), // Same radius as border for consistency
//                        clip = false
//                    )
                    .padding(
                        horizontal = 14.dp,
                        vertical = 10.dp
                    )
                    .width(130.dp)
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
                    fontWeight = FontWeight.W500,
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
                PatientCard(patient = patient)
            }
        }

    }
}

@Composable
fun PatientCard(patient: Patient) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp),
        elevation = CardDefaults.elevatedCardElevation(2.dp),
    ) {
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
        ){
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
}

//@Preview(showBackground = true)
//@Composable
//fun DashboardScreenPreview() {
//    DashboardScreen(user = User(
//        username = "admin",
//        password = "1234",
//        name = "Dr.Jane Doe",
//        role = "Chief Surgeon"
//    ))
//}