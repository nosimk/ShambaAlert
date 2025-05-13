package com.example.farmer.ui.theme.screens.password

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.farmer.R
import com.example.farmer.data.AuthViewModel
import com.example.farmer.navigation.ROUTE_CHANGEDPASSWORD
import com.example.farmer.navigation.ROUTE_SETTINGS
import com.example.farmer.ui.theme.SoftGreen

@Composable
fun PasswordScreen(navController: NavController){
    var currentpass by remember { mutableStateOf("") }
    var newpass by remember { mutableStateOf("") }
    var confirmpass by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val context = LocalContext.current
    val viewModel : AuthViewModel = viewModel()
    var passwordVisible by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.background(SoftGreen).fillMaxSize().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Spacer(Modifier.height(40.dp))
        Text(text = "Update Password:",
            fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = currentpass,
            onValueChange = {newCurrentpass -> currentpass = newCurrentpass},
            label = { Text(text = "Enter your current password:", color = Color.Black)},
            visualTransformation = if (passwordVisible) VisualTransformation.None  else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(R.drawable.show)

                else painterResource(R.drawable.hide1)
                IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(painter = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        modifier = Modifier.size(20.dp))
                }
            },
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )
        OutlinedTextField(
            value = newpass,
            onValueChange = {newNewpass -> newpass = newNewpass},
            label = { Text(text = "Enter your new password:", color = Color.Black)},
            visualTransformation = if (passwordVisible) VisualTransformation.None  else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(R.drawable.show)

                else painterResource(R.drawable.hide1)
                IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(painter = image, contentDescription = if (passwordVisible) "Hide password" else "Show password",modifier = Modifier.size(20.dp))
                }
            },
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )
        OutlinedTextField(
            value = confirmpass,
            onValueChange = {newConfirmpass -> confirmpass = newConfirmpass},
            label = { Text(text = "Enter your current password:", color = Color.Black)},
            visualTransformation = if (passwordVisible) VisualTransformation.None  else PasswordVisualTransformation(),
            trailingIcon = {
                val image = if (passwordVisible)
                    painterResource(R.drawable.show)

                else painterResource(R.drawable.hide1)
                IconButton(onClick = {passwordVisible = !passwordVisible}) {
                    Icon(painter = image,
                        contentDescription = if (passwordVisible) "Hide password" else "Show password",
                        modifier = Modifier.size(20.dp))
                }
            },
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = {

            when {
                currentpass.isBlank() || newpass.isBlank() || confirmpass.isBlank() -> {
                    Toast.makeText(context, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                }
                newpass != confirmpass-> {
                    Toast.makeText(context, "New passwords do not match", Toast.LENGTH_SHORT).show()
                }
                newpass.length < 6 -> {
                    Toast.makeText(context, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    viewModel.changePassword(
                        currentpass,
                        newpass,
                        onSuccess = {
                            Toast.makeText(context, "Password changed successfully", Toast.LENGTH_SHORT).show()
                        },
                        onError = { errorMsg ->
                            Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show()
                        }
                    )
                }
            }
            navController.navigate(ROUTE_SETTINGS)
        },
            modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text(text = "Update Password")
        }
    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PasswordScreenPreview(){
//    PasswordScreen(onPasswordChange =  )
//}