package com.example.farmer.ui.theme.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
fun RegisterScreen(navController: NavController){
    val authViewModel : AuthViewModel = viewModel()
    var fullname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MintGreen)
            .padding(20.dp)


    ){
        Text(
            text = "Create Account",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold

        )
        Spacer(modifier = Modifier.height(15.dp))
        OutlinedTextField(
            value = fullname,
            onValueChange = {newFullname->fullname=newFullname},
            label = { Text(text = "Full Name")},
            modifier = Modifier.fillMaxWidth().background(Color.White)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = phone,
            onValueChange = {newPhone->phone=newPhone},
            label = { Text(text = "Phone Number")},
            modifier = Modifier.fillMaxWidth().background(Color.White)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = email,
            onValueChange = {newEmail->email=newEmail},
            label = { Text(text = "Email")},
            modifier = Modifier.fillMaxWidth().background(Color.White)
        )
        Spacer(modifier = Modifier.height(12.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {newPassword->password=newPassword},
            label = { Text(text = "Password")},
            modifier = Modifier.fillMaxWidth().background(Color.White)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                authViewModel.signup(fullname,phone,email,password,navController, context)
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(SoftGreen)

        ) {
            Text(text = "Sign Up",
                fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height(12.dp))
        TextButton(onClick = {
            navController.navigate(ROUTE_LOGIN)
        }) {
            Text(text = "Have an account already? Log In")
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(rememberNavController())
}