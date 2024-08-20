package com.example.vitalcare.ui.screens

import android.content.Context
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vitalcare.R
import com.example.vitalcare.data.User
import com.example.vitalcare.ui.theme.VitalCareTheme
import com.example.vitalcare.util.JsonUtil

@Composable
fun LoginScreen(context: Context, onLoginSuccess: (User) -> Unit = {}) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    val users = remember { JsonUtil.loadUsers(context) }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 50.dp)
            .imePadding(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier
                .width(200.dp)
                .padding(top = 25.dp)

        )

        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Text(
//                text = "Welcome Back!",
//                color = colorResource(id = R.color.dblue),
//                fontSize = 30.sp,
//                fontWeight = FontWeight.W500,
//                modifier = Modifier.padding(bottom = 12.dp)
//            )

            Text(
                text = "We are happy to see you again",
                color = colorResource(id = R.color.lgray),
                fontSize = 18.sp,
                modifier = Modifier.padding(bottom = 36.dp)
            )

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Username", color = colorResource(id = R.color.black)) },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 20.dp)
            )

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", color = colorResource(id = R.color.black)) },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 30.dp)
            )

            Button(
                onClick = {
                    val user = users.find { it.username == username }
                    if (user != null && user.password == password) {
                        onLoginSuccess(user)
                    } else if (user != null) {
                        errorMessage = "Incorrect Password!"
                    } else {
                        errorMessage = "User Not Found!"
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.lblue),
                    contentColor = colorResource(id = R.color.white)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp)
                    .height(50.dp)
            ) {
                Text(
                    text = "Login",
                    fontSize = 20.sp,
                )
            }

            if (errorMessage.isNotEmpty()) {
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.padding(top = 8.dp),
                )
            }

            Text(
                text = "Forgot your password?",
                color = colorResource(id = R.color.dgray),
                fontSize = 15.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Don't have an account?",
                color = colorResource(id = R.color.dgray),
                fontSize = 15.sp,
            )

            Text(
                text = "Sign Up",
                color = colorResource(id = R.color.dblue),
                fontSize = 15.sp,
                modifier = Modifier
                    .padding(start = 20.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    VitalCareTheme {
        LoginScreen(context = LocalContext.current)
    }
}
