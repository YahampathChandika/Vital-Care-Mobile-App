package com.example.vitalcare

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.vitalcare.data.User
import com.example.vitalcare.ui.screens.DashboardScreen
import com.example.vitalcare.ui.screens.LoginScreen
import com.example.vitalcare.ui.theme.VitalCareTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VitalCareTheme {
                var loggedInUser by remember { mutableStateOf<User?>(null) }

                if (loggedInUser != null) {
                    DashboardScreen(user = loggedInUser!!)
                } else {
                    LoginScreen(context = this) { user ->
                        loggedInUser = user
                    }
                }
            }
        }
    }
}

