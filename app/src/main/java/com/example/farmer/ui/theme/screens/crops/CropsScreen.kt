package com.example.farmer.ui.theme.screens.crops

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.farmer.models.Crops
import com.example.farmer.ui.theme.CardWhite
import com.example.farmer.ui.theme.ForestGreen
import com.example.farmer.ui.theme.SoftGreen
import com.example.farmer.R
import com.example.farmer.models.Tip
import com.example.farmer.models.UserModel

import com.example.farmer.navigation.ROUTE_CROPFEED
import com.example.farmer.ui.theme.MintGreen
import com.example.farmer.ui.viewmodel.CropsViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.withContext
import java.util.UUID


@Composable
fun CropsScreen(navController: NavController,onPostComplete: () -> Unit){
    var cropName by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var harvest by remember { mutableStateOf("") }
    val cropsViewModel :CropsViewModel = viewModel()
    val context = LocalContext.current
    val successMessage by remember { mutableStateOf("") }
    val errorMessage by remember { mutableStateOf("") }
    val cropId = UUID.randomUUID().toString()
    val newCrops = Crops(
        cropId = cropId,
        cropName = cropName,
        duration = duration,
        harvest = harvest,

        )



//    val crops = listOf(
//        Crops("Maize", "3 months", "August 2025", R.drawable.maize),
//        Crops("Wheat", "4 months", "September 2025", R.drawable.whaetie),
//        Crops("Beans", "2 months", "July 2025", R.drawable.beans),
//        Crops("Tomatoes", "2.5 months", "July 2025", R.drawable.tomato)
//    )
    Column (
        modifier = Modifier
            .background(MintGreen)
            .fillMaxSize()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "ADD A CROP:",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        if (successMessage.isNotEmpty()) {
            Text(text = successMessage, color = Color.Green)
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (errorMessage.isNotEmpty()) {
            Text(text = errorMessage, color = Color.Red)
            Spacer(modifier = Modifier.height(8.dp))
        }
        OutlinedTextField(
            value = cropName,
            onValueChange = {newCropName -> cropName =newCropName},
            label = { Text(text = "Name of Crop")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(CardWhite)
        )
        OutlinedTextField(
            value = duration,
            onValueChange = {newDuration -> duration = newDuration },
            label = { Text(text = "How long since planted?")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(CardWhite)

        )
        OutlinedTextField(
            value = harvest,
            onValueChange = {newHarvest -> harvest = newHarvest},
            label = { Text(text = "When are you planning to harvest?")},
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(CardWhite)


        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = {
            if (cropName.isNotBlank() || duration.isNotBlank()|| harvest.isNotBlank()) {
                val currentUser = FirebaseAuth.getInstance().currentUser
                val authorId = currentUser?.uid ?: "anonymous"

                val userRef = FirebaseDatabase.getInstance().getReference("Crops").child(authorId)
                userRef.get().addOnSuccessListener { snapshot ->



                    cropsViewModel.addCrop(newCrops,
                        onSuccess = {Log.d("CropsScreen","Crop added successfully!")
                            navController.navigate(ROUTE_CROPFEED)},

                        onFailure = { error ->Log.e("CropsScreen","Error :$error") })
                    navController.popBackStack()

                    onPostComplete()
                }
            }
           },
            modifier = Modifier.padding(10.dp).fillMaxWidth(),
           colors = ButtonDefaults.buttonColors(SoftGreen))
           {
                Text(text = "SUBMIT",
                    fontSize = 20.sp,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    color = Color.White)
        }
        Button(onClick = {navController.navigate(ROUTE_CROPFEED)},
            colors = ButtonDefaults.buttonColors(SoftGreen),
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            ) {
            Text(text = "GO TO CROP FEED",
                fontSize = 20.sp,
                color = Color.White)
        }


    }
}

//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun CropsScreenPreview (){
//    CropsScreen(rememberNavController(),onPostComplete: () -> Unit)
//}