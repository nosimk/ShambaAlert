package com.example.farmer.ui.theme.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.farmer.ui.theme.SoftGreen
import com.example.farmer.R
import com.example.farmer.ui.theme.ForestGreen


@Composable
fun SplashScreen (onNavigateToNext: () -> Unit){
    val splashScreenDuration = 3000L
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(splashScreenDuration)
        onNavigateToNext()
    }
    Column (
        modifier = Modifier.background(SoftGreen).fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        Image(painter = painterResource(R.drawable.farmlogo),
            contentDescription = "")
        Text(
            text = "ShambaAlert",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = ForestGreen

        )

    }

}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun SplashScreenPreview(){
//    SplashScreen()
//}