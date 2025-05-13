package com.example.farmer.ui.theme.screens.tips

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.farmer.data.TipsViewModel
import com.example.farmer.models.Tip
import com.example.farmer.ui.theme.SoftGreen
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import java.util.UUID

@Composable
fun PostTipScreen( viewModel: TipsViewModel, onPostComplete: () -> Unit,navController: NavController){
    var content by remember { mutableStateOf("") }

    val tips = viewModel.tips



    Column (Modifier
        .padding(16.dp)
        .background(SoftGreen).fillMaxSize().fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        OutlinedTextField(
            value = content,
            onValueChange = {content = it},
            label = { Text(text = "Enter your tip") },
            modifier = Modifier.fillMaxWidth()

        )
        Spacer(Modifier.height(8.dp))
        Button(onClick = {
            if (content.isNotBlank()) {
                val currentUser = FirebaseAuth.getInstance().currentUser
                val authorId = currentUser?.uid ?: "anonymous"

                val userRef = FirebaseDatabase.getInstance().getReference("Users").child(authorId)
                userRef.get().addOnSuccessListener { snapshot ->
                    val authorName = snapshot.child("fullname").value as? String ?: "Unknown"
                    val tipId = UUID.randomUUID().toString()

                    val newTip = Tip(
                        tipId = tipId,
                        authorId = authorId,
                        authorName = authorName,
                        content = content,
                        timestamp = System.currentTimeMillis()
                    )

                    viewModel.addTip(newTip)
                    navController.popBackStack()
                    content = ""
                    onPostComplete()
                }
            }
        })

     {
            Text(text = "Post Tip")

        }


    }
    }




//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PostTipScreenPreview(){
//    PostTipScreen()
//}