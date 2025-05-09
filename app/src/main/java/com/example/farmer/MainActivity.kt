  package com.example.farmer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.farmer.api.WeatherModel
import com.example.farmer.data.WeatherViewModel
import com.example.farmer.navigation.AppNavHost
import com.example.farmer.ui.theme.FarmerTheme
import com.example.farmer.ui.theme.screens.homescreen.HomeScreen
import com.example.farmer.ui.theme.screens.weather.WeatherScreen

  class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FarmerTheme {
//
                AppNavHost()
            }
        }
    }
}

