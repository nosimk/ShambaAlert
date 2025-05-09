package com.example.farmer.ui.theme.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.farmer.R
import com.example.farmer.data.AuthViewModel
import com.example.farmer.navigation.ROUTE_EDIT
import com.example.farmer.navigation.ROUTE_POST
import com.example.farmer.navigation.ROUTE_SETTINGS
import com.example.farmer.navigation.ROUTE_START
import com.example.farmer.navigation.ROUTE_TIPS
import com.example.farmer.navigation.ROUTE_WEATHER
import com.example.farmer.ui.theme.ForestGreen
import com.example.farmer.ui.theme.MintGreen
import com.example.farmer.ui.theme.SoftGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavController,viewModel : AuthViewModel){
//    val scrollState= rememberScrollState()
//    var selectedCounty by remember { mutableStateOf("Select Region") }
//    var expanded by remember { mutableStateOf(false) }
//    var notificationsOn by remember { mutableStateOf(true) }
//    var password by remember { mutableStateOf("") }
    Scaffold ( topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MintGreen,
                titleContentColor = ForestGreen
            ),
            title = { Text(text = "ShambaAlert",
                fontSize = 30.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold
            ) },

            navigationIcon ={
                Icon(painter = painterResource(R.drawable.homed),
                    contentDescription = "Home",
                    modifier = Modifier.size(70.dp),
                    tint = ForestGreen,


                    )

            }  ,
            actions = {
            })

    }

        ,bottomBar = {
            val selectedItem = remember { mutableStateOf(0) }

            NavigationBar (containerColor = MintGreen){
                NavigationBarItem(
                    selected = selectedItem.value == 3,
                    onClick = {selectedItem.value = 3
                        navController.navigate(ROUTE_WEATHER)},
                    icon = { Image(painter = painterResource(R.drawable.weathie),
                        contentDescription = "",
                        modifier = Modifier.size(45.dp)) },


                    )
                NavigationBarItem(
                    selected = selectedItem.value == 1,
                    onClick = {selectedItem.value = 1},
                    icon = { Image(painter = painterResource(R.drawable.crops),
                        contentDescription = "",
                        modifier = Modifier.size(80.dp)) },


                    )
                NavigationBarItem(
                    selected = selectedItem.value == 2,
                    onClick = {selectedItem.value = 2
                        navController.navigate(ROUTE_POST)},
                    icon = { Image(painter = painterResource(R.drawable.adds),
                        contentDescription = "",
                        modifier = Modifier.size(60.dp)) },

                    )
                NavigationBarItem(
                    selected = selectedItem.value == 0,
                    onClick = {selectedItem.value = 0 },
                    icon = { Image(painter = painterResource(R.drawable.settings),
                        contentDescription = "",
                        modifier = Modifier.size(45.dp)) },

                    )
                NavigationBarItem(
                    selected = selectedItem.value == 2,
                    onClick = {selectedItem.value = 2 },
                    icon = { Image(painter = painterResource(R.drawable.image), contentDescription = "",
                        modifier = Modifier.size(60.dp).clickable { navController.navigate(ROUTE_TIPS) }) },

                    )
            }
        }){
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize().background(SoftGreen),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){}




    Column (
        modifier = Modifier.fillMaxSize().padding(20.dp),
        verticalArrangement = Arrangement.Center
    ){
        Row (){
            Icon(imageVector = Icons.Filled.Person,
                contentDescription = "Person," ,
                modifier = Modifier.size(150.dp),
                tint = Color.White)
            Column (
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ){
                Spacer(Modifier.height(30.dp))
                Text(
                    text = "Name",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold

                )
                Text(
                    text = "Email Address",
                    fontSize = 15.sp
                )

            }

        }

        Row (){
            Icon(
                painter = painterResource(R.drawable.editp),
                contentDescription = "",
                modifier = Modifier.size(70.dp)
            )
            TextButton(onClick = {navController.navigate(ROUTE_EDIT)}) {
                Text(text = "Edit profile",
                    fontSize = 20.sp)
            }


        }
        Row (){
            Icon(painter = painterResource(R.drawable.password),
                contentDescription = "",
                modifier = Modifier.size(70.dp))

            TextButton(onClick = {



            }) {
                Text(text = "Change Password",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 10.dp))
            }
        }
        Row (){
            Icon(painter = painterResource(R.drawable.logout),
                contentDescription = "",
                modifier = Modifier.size(70.dp).clickable {
                        viewModel.logout()
                        navController.navigate(ROUTE_START){
                            popUpTo(ROUTE_SETTINGS){ inclusive = true } }
                    }

                )
            TextButton(onClick = {
                viewModel.logout()
                navController.navigate(ROUTE_START){
                    popUpTo(ROUTE_SETTINGS){ inclusive= true}}})

            {
                Text(text = "Log Out",
                    fontSize = 20.sp,
                    modifier = Modifier.padding(vertical = 10.dp))
            }
        }




                }
            }}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ProfileScreenPreview(){


    ProfileScreen(rememberNavController(), viewModel = AuthViewModel())
}