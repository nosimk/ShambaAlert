package com.example.farmer.ui.theme.screens.start

import androidx.compose.foundation.Image
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
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.farmer.R
import com.example.farmer.navigation.ROUTE_LOGIN
import com.example.farmer.ui.theme.MintGreen
import com.example.farmer.ui.theme.SoftGreen

@Composable
fun StartUpScreen(navController: NavController){
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MintGreen).padding(20.dp)){
        Text(text="Welcome!",
            modifier =Modifier.fillMaxWidth(),
            fontSize = 50.sp,
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(12.dp))
        Image(
            painter = painterResource(R.drawable.weather),
            contentDescription = "Weather",
            modifier = Modifier.height(250.dp)
        )
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {navController.navigate(ROUTE_LOGIN)},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(SoftGreen)
        ) {
            Text(text="Sign Up",
                fontSize = 20.sp)

        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {navController.navigate(ROUTE_LOGIN)},
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(SoftGreen)
        ) {
            Text(text="Log In",
                fontSize = 20.sp)

        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StartUpScreenPreview(){
    StartUpScreen(rememberNavController())
}