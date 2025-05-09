package com.example.farmer.ui.theme.screens.password

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmer.ui.theme.SoftGreen

@Composable
fun PasswordScreen(onPasswordChange: (String, String, String) -> Unit){
    var currentpass by remember { mutableStateOf("") }
    var newpass by remember { mutableStateOf("") }
    var confirmpass by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }

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
            label = { Text(text = "Enter your current password:")},
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )
        OutlinedTextField(
            value = newpass,
            onValueChange = {newNewpass -> newpass = newNewpass},
            label = { Text(text = "Enter your new password:")},
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )
        OutlinedTextField(
            value = confirmpass,
            onValueChange = {newConfirmpass -> confirmpass = newConfirmpass},
            label = { Text(text = "Enter your current password:")},
            modifier = Modifier.fillMaxWidth().padding(10.dp)
        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = {

            Log.d("ChangePassword", "Button clicked with currentPassword: $currentpass, newPassword: $newpass, confirmPassword: $confirmpass")

            if (newpass == confirmpass) {

                onPasswordChange(currentpass, newpass   , confirmpass)
            } else {
                message = "Passwords do not match"
            }
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