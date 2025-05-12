package com.example.farmer.ui.theme.screens.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.magnifier
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.farmer.R
import com.example.farmer.api.NetworkResponse
import com.example.farmer.api.WeatherModel
import com.example.farmer.data.WeatherViewModel
import com.example.farmer.navigation.ROUTE_POST
import com.example.farmer.navigation.ROUTE_SETTINGS
import com.example.farmer.navigation.ROUTE_TIPS
import com.example.farmer.navigation.ROUTE_WEATHER
import com.example.farmer.ui.theme.ForestGreen
import com.example.farmer.ui.theme.MintGreen
import com.example.farmer.ui.theme.SoftGreen
import com.example.farmer.ui.theme.TextDark

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(viewModel: WeatherViewModel,navController: NavController ){
    var city by remember { mutableStateOf("")}
    val weatherResult = viewModel.weatherResult.observeAsState()
    val keyboardController = LocalSoftwareKeyboardController.current
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
                    modifier = Modifier.size(70.dp).clickable { navController.navigate(ROUTE_TIPS) },
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
                    selected = selectedItem.value == 2,
                    onClick = {selectedItem.value = 2 },
                    icon = { Image(painter = painterResource(R.drawable.settings), contentDescription = "",
                        modifier = Modifier.size(60.dp).clickable { navController.navigate(
                            ROUTE_SETTINGS
                        ) }) },

                    )
            }
        }){
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize().background(SoftGreen),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center

        ) {

    Column (
        modifier = Modifier.fillMaxWidth().padding(8.dp).background(SoftGreen).fillMaxSize().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ){
        Row (
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ){
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city,
                onValueChange = {newCity->city=newCity},
                label = { Text(text = "Search for location",
                    color = TextDark)
                }
            )
            IconButton(onClick = {
                viewModel.getData(city)
                keyboardController?.hide()

            }) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "")
            }
        }
        when (val result = weatherResult.value){
            is NetworkResponse.Error -> {
                Text(text = result.message)

            }
            NetworkResponse.Loading -> {
                CircularProgressIndicator()
            }
            is NetworkResponse.Success-> {
                WeatherDetails(data = result.data)
            }
            null -> {}
        }
    }
}}}
@Composable
fun WeatherDetails(data : WeatherModel){
    Column (
        modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp).background(SoftGreen),
        horizontalAlignment = Alignment.CenterHorizontally,

    ){
        Row (
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Bottom
            ){
            Icon(
                imageVector = Icons.Default.LocationOn,
                contentDescription ="" ,
                modifier = Modifier.size(40.dp)
            )
            Text(text = data.location.name,
                fontSize = 30.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = data.location.country,
                fontSize = 18.sp,
                color = Color.Gray
            )

        }
        Spacer(Modifier.height(16.dp))
        Text(text = "${data.current.temp_c} °C",
            fontSize =  56.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        AsyncImage(
            model = "https:${data.current.condition.icon}".replace("64x64","128x128"),
            contentDescription = "",
            )
        Text(text = data.current.condition.text,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = Color.Gray)

        Spacer(Modifier.height(16.dp))
        Card (){
            Column (modifier = Modifier.fillMaxWidth()){
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    WeatherKeyValue("Humidity",data.current.humidity)
                    WeatherKeyValue("Wind Speed",data.current.wind_kph+"km/h")


                }
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    WeatherKeyValue("UV light",data.current.uv)
                    WeatherKeyValue("Parcipation",data.current.precip_mn+"mm")


                }
                Row (modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ){
                    WeatherKeyValue("Local Time",data.location.localtime.split(" ")[1])
                    WeatherKeyValue("Local Date",data.location.localtime.split(" ")[0])


                }
            }
        }

            }
        }


@Composable
fun WeatherKeyValue(key : String,value : String){
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Text(text = value,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold)
        Text(text = key, fontWeight = FontWeight.SemiBold,
            color = Color.Gray)
    }

}
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun WeatherScreenPreview(){
//    WeatherScreen()
//}
