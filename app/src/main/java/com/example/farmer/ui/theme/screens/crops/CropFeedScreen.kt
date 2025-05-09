package com.example.farmer.ui.theme.screens.crops

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmer.ui.theme.PurpleGrey40
import com.example.farmer.ui.theme.SoftGreen
import com.example.farmer.R
import com.example.farmer.ui.theme.ForestGreen
import com.example.farmer.ui.theme.MintGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CropFeed(){
    Column (modifier = Modifier.background(SoftGreen).fillMaxSize().fillMaxWidth()){
        Scaffold (
            topBar = {
                TopAppBar(
                    title = { Text("My Crops")},
                    colors = TopAppBarDefaults.topAppBarColors(MintGreen)
                )
            }
        ){ innerPadding ->
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier.fillMaxSize().padding(innerPadding)
            ) {
                items(crops){ crop ->
                    CropsScreen(crop)
                }
            }
        }

}}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CropFeedScreen(){
    CropFeed()
}