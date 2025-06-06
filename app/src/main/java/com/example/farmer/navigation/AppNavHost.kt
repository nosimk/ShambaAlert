package com.example.farmer.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.farmer.data.AuthViewModel
import com.example.farmer.data.TipsViewModel
import com.example.farmer.data.WeatherViewModel
import com.example.farmer.models.Crops
import com.example.farmer.models.Tip
import com.example.farmer.ui.theme.screens.homescreen.HomeScreen

import com.example.farmer.ui.theme.screens.login.LogInScreen
import com.example.farmer.ui.theme.screens.register.RegisterScreen
import com.example.farmer.ui.theme.screens.settings.ProfileScreen
import com.example.farmer.ui.theme.screens.SplashScreen
import com.example.farmer.ui.theme.screens.crops.CropFeed
import com.example.farmer.ui.theme.screens.crops.CropsScreen
import com.example.farmer.ui.theme.screens.password.ChangedPasswordScreen
import com.example.farmer.ui.theme.screens.password.PasswordScreen

import com.example.farmer.ui.theme.screens.start.StartUpScreen
import com.example.farmer.ui.theme.screens.tips.FeedScreen
import com.example.farmer.ui.theme.screens.tips.PostTipScreen
import com.example.farmer.ui.theme.screens.weather.WeatherScreen
import com.example.farmer.ui.viewmodel.CropsViewModel


@Composable
fun AppNavHost(startDestination:String= ROUTE_START){
    val TipsViewModel = TipsViewModel()
    val navController = rememberNavController()

    NavHost(navController=navController,startDestination=startDestination){
        composable(ROUTE_SPLASH){ SplashScreen{
            navController.navigate(ROUTE_START){
                popUpTo(ROUTE_SPLASH){inclusive=true}} } }

        composable(ROUTE_REGISTER) { RegisterScreen(navController) }
        composable(ROUTE_LOGIN) { LogInScreen(navController) }
        composable(ROUTE_START) { StartUpScreen(navController) }
        composable(ROUTE_SETTINGS) { ProfileScreen(navController, viewModel = AuthViewModel()) }
        composable(ROUTE_HOMESCREEN){ HomeScreen(navController) }
        composable(ROUTE_WEATHER) { WeatherScreen(viewModel = WeatherViewModel(),navController = navController)  }
        composable(ROUTE_POST) { PostTipScreen(viewModel = TipsViewModel,navController = navController, onPostComplete = {})}

        composable(ROUTE_TIPS) { FeedScreen(viewModel = TipsViewModel,navController =navController, onDelete = {}) }

        composable(ROUTE_CROPFEED) { CropFeed(navController = navController, viewModel = CropsViewModel())  }
        composable(ROUTE_CROPFORM) { CropsScreen(navController =navController, onPostComplete = {}) }
        composable(ROUTE_PASSWORD) { PasswordScreen(navController = navController)  }
        composable(ROUTE_CHANGEDPASSWORD) { ChangedPasswordScreen(navController = navController,
            viewModel = AuthViewModel())  }

        }
    }
