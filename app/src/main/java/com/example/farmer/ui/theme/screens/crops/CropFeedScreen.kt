package com.example.farmer.ui.theme.screens.crops

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.farmer.ui.theme.PurpleGrey40
import com.example.farmer.ui.theme.SoftGreen
import com.example.farmer.R
import com.example.farmer.models.Crops
import com.example.farmer.navigation.ROUTE_POST
import com.example.farmer.navigation.ROUTE_TIPS
import com.example.farmer.navigation.ROUTE_WEATHER
import com.example.farmer.ui.theme.ForestGreen
import com.example.farmer.ui.theme.MintGreen
import com.example.farmer.ui.viewmodel.CropsViewModel
import kotlin.time.Duration
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import com.example.farmer.navigation.ROUTE_CROPFORM
import com.example.farmer.navigation.ROUTE_SETTINGS
import java.util.UUID


@OptIn(ExperimentalMaterial3Api::class)
@Composable

fun CropFeed(viewModel: CropsViewModel = viewModel(),navController: NavController) {

    val crops by viewModel.crops.collectAsState()





    Scaffold ( topBar = {
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = MintGreen,
                titleContentColor = ForestGreen
            ),
            title = { Text(text = "ShambaAlert",
                fontSize = 30.sp,
                fontStyle = FontStyle.Normal,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.clickable { navController.navigate(ROUTE_TIPS) }
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
                    onClick = {selectedItem.value = 1
                              navController.navigate(ROUTE_CROPFORM)},
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
                        )
                        }) },

                    )
            }
        }){
            padding ->
        LazyColumn(contentPadding = padding) {
            items(crops) { crop ->
                CropCard(crop)
            }
        }
            }
        }

@Composable
fun CropCard(crops: Crops){
    val cropsViewModel :CropsViewModel = viewModel()
    cropsViewModel.addCrop (
            crops,
            onSuccess ={Log.d("CropCardScreen","Crop Added Successfully")},
            onFailure = { error -> Log.e("FeedScreen","Error adding crop :$error")}
           )

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//

            Text(text = "Name : ${crops.cropName}", fontWeight = FontWeight.Bold)
            Text(text = "Duration: ${crops.duration}")
            Text(text = "Harvest: ${crops.harvest}")
           Icon(
               painter = painterResource(R.drawable.deletes),
               contentDescription = "",
               modifier = Modifier.size(20.dp).clickable { cropsViewModel.deleteCrop(crops.cropId)  }
           )



        }

    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CropFeedScreen(){
    CropFeed( viewModel=CropsViewModel(),rememberNavController())
}