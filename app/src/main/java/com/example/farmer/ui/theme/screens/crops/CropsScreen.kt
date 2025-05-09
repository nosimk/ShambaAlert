package com.example.farmer.ui.theme.screens.crops

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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.farmer.models.Crops
import com.example.farmer.ui.theme.CardWhite
import com.example.farmer.ui.theme.ForestGreen
import com.example.farmer.ui.theme.SoftGreen
import com.example.farmer.R

@Composable
fun CropsScreen(){
    var cropName by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf("") }
    var harvest by remember { mutableStateOf("") }
    val crops = listOf(
        Crops("Maize","3 months","August 2025",R.drawable.maize)
    )
    Column (
        modifier = Modifier.background(SoftGreen).fillMaxSize().fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "ADD A CROP:",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            value = cropName,
            onValueChange = {newCropName -> cropName =newCropName},
            label = { Text(text = "Name of Crop")},
            modifier = Modifier.fillMaxWidth().padding(10.dp).background(CardWhite)
        )
        OutlinedTextField(
            value = duration,
            onValueChange = {newDuration -> duration = newDuration },
            label = { Text(text = "How long since planted?")},
            modifier = Modifier.fillMaxWidth().padding(10.dp).background(CardWhite)

        )
        OutlinedTextField(
            value = harvest,
            onValueChange = {newHarvest -> harvest = newHarvest},
            label = { Text(text = "When are you planning to harvest?")},
            modifier = Modifier.fillMaxWidth().padding(10.dp).background(CardWhite)


        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = {},
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
    CropsScreen()
}