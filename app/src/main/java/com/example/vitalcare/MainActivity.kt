package com.example.vitalcare

import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vitalcare.data.Patient
import com.example.vitalcare.data.User
import com.example.vitalcare.navigation.NavRoutes
import com.example.vitalcare.ui.screens.AddPatientScreen
import com.example.vitalcare.ui.screens.AddVitalsScreen
import com.example.vitalcare.ui.screens.DashboardScreen
import com.example.vitalcare.ui.screens.LoginScreen
import com.example.vitalcare.ui.screens.PatientDetailScreen
import com.example.vitalcare.ui.theme.VitalCareTheme
import com.google.gson.Gson

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VitalCareTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = NavRoutes.Login) {
                    composable(NavRoutes.Login) {
                        LoginScreen(context = this@MainActivity) { user ->
                            val userJson = Gson().toJson(user)
                            navController.navigate("${NavRoutes.Dashboard}/$userJson") {
                                popUpTo(NavRoutes.Login) { inclusive = true }
                            }
                        }
                    }

                    composable("${NavRoutes.Dashboard}/{userJson}") { backStackEntry ->
                        val userJson = backStackEntry.arguments?.getString("userJson")
                        val user = Gson().fromJson(userJson, User::class.java)
                        if (user != null) {
                            DashboardScreen(user = user, navController = navController)
                        }
                    }

                    composable(NavRoutes.AddPatient) {
                        AddPatientScreen(context = this@MainActivity, navController = navController)
                    }

                    composable("${NavRoutes.PatientDetail}/{patientJson}") { backStackEntry ->
                        val patientJson = backStackEntry.arguments?.getString("patientJson")
                        val patient = Gson().fromJson(patientJson, Patient::class.java)
                        PatientDetailScreen(patient = patient, navController = navController)
                    }

                    composable("${NavRoutes.AddVitals}/{patientId}") { backStackEntry ->
                        val patientId = backStackEntry.arguments?.getString("patientId") ?: return@composable
                        AddVitalsScreen(patientId = patientId, navController = navController)
                    }

                }
            }
        }
    }
}