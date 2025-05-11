package com.example.farmer.ui.theme.screens.settings

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.example.farmer.R
import com.example.farmer.data.AuthViewModel
import com.example.farmer.models.UserModel
import com.example.farmer.models.UserProfile
import com.example.farmer.navigation.ROUTE_POST
import com.example.farmer.navigation.ROUTE_TIPS
import com.example.farmer.navigation.ROUTE_WEATHER
import com.example.farmer.ui.theme.ForestGreen
import com.example.farmer.ui.theme.MintGreen
import com.example.farmer.ui.theme.SoftGreen
import com.example.farmer.ui.viewmodel.CropsViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
// Jetpack Compose
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*

import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.draw.clip

// Accompanist (or Coil) for image loading
import coil.compose.AsyncImage




import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.tasks.await


import android.content.Context

import android.util.Base64
import android.widget.Toast


import androidx.activity.compose.rememberLauncherForActivityResult
import com.example.farmer.data.ProfileViewModel
import androidx.lifecycle.viewmodel.compose.viewModel


import okhttp3.*
import org.json.JSONObject
import java.io.IOException



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProfileScreen(navController: NavController,

){  val viewModel :ProfileViewModel = viewModel()
    val context = LocalContext.current
    val user by viewModel.userData.collectAsState()
    val profile by viewModel.profileImage.collectAsState()

    var fullname by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var profileUrl by remember { mutableStateOf("") }
    val imageUri = rememberSaveable() { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? -> uri?.let { imageUri.value=it } }
    val selectedImageUri :Uri? = null

    val imagePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            viewModel.uploadToImgur(context, it) { link ->
                link?.let { uploadedUrl ->
                    profileUrl = uploadedUrl
                }
            }
        }
    }

    LaunchedEffect(Unit) {
        viewModel.fetchUserData()
        viewModel.fetchProfileImage()
    }

    LaunchedEffect(user, profile) {
        user?.let {
            fullname = it.fullname
            email = it.email
            phone = it.phone
        }
        profile?.let {
            profileUrl = it.profilePictureUrl
        }
    }


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
                    modifier = Modifier.size(60.dp).clickable { navController.navigate(ROUTE_TIPS) }) },

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
            Column {
                Spacer(Modifier.height(16.dp))
                Card (
                    modifier = Modifier.size(200.dp).align(Alignment.CenterHorizontally),
                    shape = CircleShape,

                    ){

                        AsyncImage(
                            model =imageUri.value ?: R.drawable.profilepics,
                            contentDescription = "Profile Image",
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)

                                .clickable { launcher.launch("image/*") })


                    }

                Spacer(Modifier.height(16.dp))
                Text(text = "Add a profile picture",
                    fontSize = 20.sp,
                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )
                Spacer(Modifier.size(16.dp))
                OutlinedTextField(
                    value = fullname,
                    onValueChange = { newFullname -> fullname = newFullname},
                    label = { Text("Full Name") }
                )
                Spacer(Modifier.size(12.dp))
                OutlinedTextField(
                    value = phone,
                    onValueChange = {newPhone -> phone = newPhone},
                    label = { Text(text = "Phone Number")}
                )
                Spacer(Modifier.size(12.dp))
                OutlinedTextField(
                    value = email,
                    onValueChange = {newEmail -> email = newEmail},
                    label = { Text(text = "Email")}
                )
                Spacer(Modifier.size(16.dp))
                Button(onClick = {
                    selectedImageUri?.let { uri ->
                        viewModel.uploadToImgur(context, uri) { uploadedUrl ->
                            if (uploadedUrl != null) {
                                viewModel.updateProfile(fullname,email, phone, uploadedUrl)
                            } else {
                                viewModel.updateProfile(fullname, email ,phone, profileUrl) // fallback
                            }
                        }
                    } ?: run {
                        // No image picked
                        viewModel.updateProfile(fullname,email, phone, profileUrl)
                    }

                },
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    colors = ButtonDefaults.buttonColors(ForestGreen)) {
                    Text(text = "Edit Profile")

                }



            }



        }

    }















        }






//
//@Preview
//@Composable
//fun EditProfileScreenPreview(){
//    EditProfileScreen(rememberNavController(), viewModel = AuthViewModel())
