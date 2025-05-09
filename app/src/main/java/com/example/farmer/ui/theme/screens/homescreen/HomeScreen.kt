package com.example.farmer.ui.theme.screens.homescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.farmer.ui.theme.LightGreen
import com.example.farmer.ui.theme.MintGreen
import com.example.farmer.ui.theme.PaleTeal
import com.example.farmer.ui.theme.SoftGreen
import com.example.farmer.R
import com.example.farmer.navigation.ROUTE_TIPS
import com.example.farmer.navigation.ROUTE_WEATHER
import com.example.farmer.ui.theme.CardWhite
import com.example.farmer.ui.theme.ForestGreen
import com.example.farmer.ui.theme.SkyBlue
import com.example.farmer.ui.theme.SunshineYellow
import com.example.farmer.ui.theme.TextDark
import com.example.farmer.ui.theme.TextMedium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavController){
    val scrollState = rememberScrollState()
    Column (
        modifier = Modifier.fillMaxSize().background(SoftGreen).verticalScroll(scrollState)
    ){
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = (ForestGreen),
                titleContentColor = Color.White
            ),
            title = {
                Text(text = "ShambaAlert",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = (SoftGreen))
            },

            navigationIcon ={ IconButton(onClick = {}){
                Icon(imageVector = Icons.Filled.Home,
                    contentDescription = "Home", tint = (SoftGreen)) } } ,
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Filled.Settings,
                        contentDescription = "Menu",
                        tint =(SoftGreen)
                    )
                }

            })
        Spacer(modifier = Modifier.height(12.dp))
        Column (
            modifier = Modifier.fillMaxSize().fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Card(
                onClick = {},

                modifier = Modifier.fillMaxWidth().padding(25.dp)

            ){
               Row (
                   Modifier.padding(10.dp)

               ){
                   Icon(painter = painterResource(R.drawable.ic_tips),
                       contentDescription = "",
                       modifier = Modifier.size(50.dp),
                       tint = (SkyBlue))
                   Spacer(Modifier.width(6.dp))
                   Text(text = "Tip of the day:",
                       maxLines = 1,
                       overflow = TextOverflow.Ellipsis,
                       fontSize = 40.sp,
                       fontWeight = FontWeight.Bold,
                       color = (TextMedium))

               }

                Text(
                    text = "Test your soil often.",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.W400,
                    lineHeight = 22.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(15.dp).fillMaxWidth().align(Alignment.CenterHorizontally)
                )
                Button(onClick = {navController.navigate(ROUTE_TIPS)},
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    colors = ButtonDefaults.buttonColors(ForestGreen))

                    {
                    Text(text = "VIEW OTHER TIPS",
                        color = SoftGreen,
                        fontSize = 15.sp)
                }
            }
            Card(
                modifier = Modifier.width(338.dp)

            ) {
                Row (
                    modifier = Modifier.padding(20.dp)
                ){
                    Image(painter = painterResource(R.drawable.sunny),
                        contentDescription = "",
                        modifier = Modifier.size(60.dp),
                        )
                    Spacer(modifier = Modifier.width(15.dp))
                    Text(text = "Weather",
                        fontSize = 40.sp,
                        fontWeight = FontWeight.Bold,
                        color = (TextMedium)
                    )
                }

                Column (
                    horizontalAlignment = Alignment.CenterHorizontally,


                ){
                    Text(text = "Today : 25°C",
                        fontSize = 30.sp
                        )
                    Text(text = "Maua, Meru County",
                        color = TextMedium,
                        fontSize = 25.sp,
                        modifier = Modifier.padding(10.dp)
                        )
                    Button(onClick = {navController.navigate(ROUTE_WEATHER)},
                        Modifier.fillMaxWidth().padding(10.dp),
                        colors = ButtonDefaults.buttonColors(ForestGreen)) {
                        Text(text = "VIEW FULL WEATHER",
                            color = SoftGreen,
                            fontSize = 15.sp
                            )
                    }
                }


            }
            Card(modifier = Modifier.fillMaxWidth().height(250.dp).width(338.dp).padding(25.dp)){
                Row (
                    modifier = Modifier.padding(20.dp)
                ){
                    Image(painter = painterResource(R.drawable.cropie),
                        contentDescription = "",
                        modifier = Modifier.size(70.dp))
                    Spacer(modifier = Modifier.width(20.dp))
                   Text(text = "Crops",
                       fontSize = 40.sp,
                       fontWeight = FontWeight.Bold,
                       color = TextMedium,
                       )

                    }
                Button(onClick = {},
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
                    colors = ButtonDefaults.buttonColors(ForestGreen))

                {
                    Text(text = "ADD A CROP",
                        color = SoftGreen,
                        fontSize = 15.sp)
                }


                }



            }
        }

   }



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun HomeScreenPreview(){
    HomeScreen(rememberNavController())
}