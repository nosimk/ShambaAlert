package com.example.farmer.ui.theme.screens.tips


import android.content.Intent
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Share

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.res.painterResource

import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.farmer.ui.theme.ForestGreen
import com.example.farmer.R
import com.example.farmer.data.TipsViewModel
import com.example.farmer.models.Tip
import com.example.farmer.navigation.ROUTE_CROPFORM
import com.example.farmer.navigation.ROUTE_POST
import com.example.farmer.navigation.ROUTE_SETTINGS
import com.example.farmer.navigation.ROUTE_TIPS
import com.example.farmer.navigation.ROUTE_WEATHER
import com.example.farmer.ui.theme.CardWhite
import com.example.farmer.ui.theme.MintGreen
import com.example.farmer.ui.theme.SoftGreen
import com.google.firebase.auth.FirebaseAuth
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedScreen(viewModel: TipsViewModel,navController: NavController,onDelete: (String) -> Unit){
    val selectedItem = remember { mutableStateOf(0) }
    val tips = viewModel.tips.observeAsState(emptyList())
    var context = LocalContext.current



    Scaffold ( bottomBar = {
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
                onClick = {selectedItem.value = 2
                          navController.navigate(ROUTE_SETTINGS)},
                icon = { Image(painter = painterResource(R.drawable.settings), contentDescription = "",
                    modifier = Modifier.size(60.dp)) },

                )
        }
    }){
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize().background(SoftGreen)

        ) {




    }
        Box( modifier = Modifier.background(SoftGreen)){
            Column {
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
                            modifier = Modifier.size(70.dp).clickable { navController.navigate(
                                ROUTE_TIPS) },
                            tint = ForestGreen) }  ,
                    actions = {})
                TipsFeed(tips = tips.value, onDelete = { tipId ->viewModel.deleteTip(tipId)})

        }

}
    }}
@Composable
fun TipsFeed(tips: List<Tip>,onDelete: (String) -> Unit) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(tips) { tip ->
            TipCard(tip = tip, onDelete = {onDelete(tip.tipId)} )
        }
    }
}

@Composable
fun TipCard(tip: Tip,onDelete: (String) -> Unit

) {
    var context = LocalContext.current
    val currentUserId = FirebaseAuth.getInstance().currentUser?.uid
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),

        elevation = CardDefaults.cardElevation(4.dp),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = tip.authorName, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = tip.content)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = SimpleDateFormat("HH:mm • dd MMM", Locale.getDefault()).format(Date(tip.timestamp)),
                style = MaterialTheme.typography.labelSmall,
                color = Color.Gray
            )
            Spacer(Modifier.height(8.dp))

            Row  (horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()){
                IconButton(
                    onClick = {
                        val shareIntent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT,"${tip.authorName} says:${tip.content}")
                            type = "text/plain`"
                        }
                        context.startActivity(Intent.createChooser(shareIntent,"Share via"))
                    }
                ) {
                    Icon(painter = painterResource(R.drawable.share),
                        contentDescription = "Share",
                        tint = Color.White,
                        modifier = Modifier.size(20.dp))

                }
                if (tip.authorId == currentUserId) {
                    IconButton(onClick = {onDelete(tip.tipId)}) {
                        Icon(painter = painterResource(R.drawable.deletes),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp))
                    }
                }
            }

        }

    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun FeedScreenPreview(){
//    FeedScreen(viewModel = TipsViewModel(),)
//}