package com.example.farmer.ui.theme.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.farmer.data.AuthViewModel
import com.example.farmer.navigation.ROUTE_LOGIN
import com.example.farmer.navigation.ROUTE_REGISTER
import com.example.farmer.ui.theme.MintGreen
import com.example.farmer.ui.theme.SoftGreen

@Composable
fun LogInScreen(navController: NavController){
    val authViewModel : AuthViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column (
        modifier = Modifier.fillMaxSize().background(MintGreen).padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(text = "Welcome Back!",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {newEmail->email=newEmail},
            label = { Text(text = "Email")},
            modifier = Modifier.fillMaxWidth().background(Color.White)
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {newPassword->password=newPassword},
            label = { Text(text = "Password")},
            modifier = Modifier.fillMaxWidth().background(Color.White)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                authViewModel.login(email,password, navController, context)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(SoftGreen)

        ) {
            Text(text = "Log In",
                fontSize = 20.sp)
        }
        TextButton(onClick = {
            navController.navigate( ROUTE_REGISTER)
        }) {
            Text(text = "Don't have an account? Sign Up")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LogInScreenPreview(){
    LogInScreen(rememberNavController())
}