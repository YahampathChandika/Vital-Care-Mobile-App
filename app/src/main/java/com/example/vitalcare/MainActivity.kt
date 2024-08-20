package com.example.vitalcare

import androidx.activity.ComponentActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.vitalcare.data.User
import com.example.vitalcare.navigation.NavRoutes
import com.example.vitalcare.ui.screens.DashboardScreen
import com.example.vitalcare.ui.screens.LoginScreen
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
                        val loggedInUser = Gson().fromJson(userJson, User::class.java)
                        if (loggedInUser != null) {
                            DashboardScreen(user = loggedInUser)
                        }
                    }
                }
            }
        }
    }
}
