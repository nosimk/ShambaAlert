package com.example.farmer.ui.theme.screens.crops

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropFeed(navController: NavController) {
    val cropsViewModel: CropsViewModel= viewModel()
    val crops = cropsViewModel.crops.observeAsState(emptyList())



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
                        modifier = Modifier.size(60.dp).clickable { navController.navigate(
                            ROUTE_TIPS
                        )
                        }) },

                    )
            }
        }){
            innerPadding ->
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(innerPadding)
        ) {
            items(crops.value.orEmpty().withIndex().toList()) { (index ,crop)->
                CropCard(crop)
            }

        }}}
@Composable
fun CropCard(crop: Crops){
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),

    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
//            Image(
//                painter = painterResource(id = crops.imageRes),
//                contentDescription = crops.cropName,
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .height(120.dp)
//                    .fillMaxWidth()
//            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = crop.cropName, fontWeight = FontWeight.Bold)
            Text(text = "Duration: ${crop.duration}")
            Text(text = "Harvest: ${crop.harvest}")
        }
    }
}





@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CropFeedScreen(){
    CropFeed(rememberNavController())
}