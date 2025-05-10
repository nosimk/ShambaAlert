package com.example.farmer.ui.theme.screens.crops

import android.util.Log
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
import com.example.farmer.ui.viewmodel.CropsViewModel
import kotlinx.coroutines.withContext


@Composable
fun CropsScreen(navController: NavController){
    var cropName by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var harvest by remember { mutableStateOf("") }
    val cropsViewModel :CropsViewModel = viewModel()
    val context = LocalContext.current
    var successMessage by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
//    val crops = listOf(
//        Crops("Maize", "3 months", "August 2025", R.drawable.maize),
//        Crops("Wheat", "4 months", "September 2025", R.drawable.whaetie),
//        Crops("Beans", "2 months", "July 2025", R.drawable.beans),
//        Crops("Tomatoes", "2.5 months", "July 2025", R.drawable.tomato)
//    )
    Column (
        modifier = Modifier
            .background(SoftGreen)
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
            if (cropName.isNotBlank() && duration.isNotBlank() && harvest.isNotBlank()) {
                val crop = Crops(cropName, duration, harvest)
                cropsViewModel.addCrop(
                    crop,
                    onSuccess = {
                        successMessage = "Crop added successfully!"
                        errorMessage = ""
                    },
                    onFailure = { error ->
                        successMessage = ""
                        errorMessage = "Failed to add crop: $error"
                    }
                )
            } else {
                errorMessage = "Please fill in all fields."
            }
            navController.navigate(R)
        },
           colors = ButtonDefaults.buttonColors(ForestGreen))
           {
                Text(text = "SUBMIT",
                    fontSize = 20.sp)
        }


    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CropsScreenPreview (){
    CropsScreen(rememberNavController())
}