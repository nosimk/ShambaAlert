package com.example.farmer.data

import android.app.VoiceInteractor
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

import com.example.farmer.models.UserModel
import com.example.farmer.models.UserProfile
import com.example.farmer.navigation.ROUTE_HOMESCREEN
import com.example.farmer.navigation.ROUTE_LOGIN
import com.example.farmer.navigation.ROUTE_TIPS
import com.example.farmer.network.ImgurApiService

import com.example.farmer.network.RetrofitClient
import com.google.firebase.Firebase
import com.google.firebase.auth.EmailAuthProvider


import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import java.io.File
import java.io.IOException
import java.util.UUID


class AuthViewModel:ViewModel() {
    private val mAuth : FirebaseAuth = FirebaseAuth.getInstance()
    private val _isloading = MutableStateFlow(false)
    private val _errorMessage = MutableStateFlow<String?>(null)
    private val _userName = MutableLiveData<String>()
    val userName: LiveData<String> get() = _userName

    private val _userEmail = MutableLiveData<String>()
    val userEmail: LiveData<String> get() = _userEmail

    private val _userPhone = MutableLiveData<String>()
    val userPhone: LiveData<String> get() = _userPhone

    private val _userProfilePicture = MutableLiveData<UserProfile>()
    val userProfilePicture: LiveData<UserProfile> get() = _userProfilePicture
    private val database = FirebaseDatabase.getInstance()
    private val storage = FirebaseStorage.getInstance()
    private val userId = "yourUserId" // Replace with actual user ID
    private lateinit var imageUri: Uri // Stores selected image URI
    private val storageRef = storage.reference.child("profile_images/${UUID.randomUUID()}.jpg")







    fun signup(fullname:String,phone:String,email :String,password :String,
               navController: NavController,
               context: Context){
        if (fullname.isBlank() || phone.isBlank() || email.isBlank() || password.isBlank()){
            Toast.makeText(context,"Please fill all the fields",Toast.LENGTH_LONG).show()

            return
        }
        _isloading.value = true

        mAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener { task->
                _isloading.value = false
                if (task.isSuccessful){
                    val userId = mAuth.currentUser?.uid?:""
                    val userData = UserModel(fullname=fullname,phone=phone,email=email,password=password,userId=userId)
                    saveUserToDataBase(userId,userData,navController,context)

                }else{
                    _errorMessage.value = task.exception?.message

                    Toast.makeText(context,"Registration failed:${task.exception?.message} ",Toast.LENGTH_LONG).show()
                }
            }
    }
    fun saveUserToDataBase(userId :String,userData:UserModel,navController: NavController,context: Context){
        val regRef = FirebaseDatabase.getInstance().getReference("Users/$userId")
        regRef.setValue(userData).addOnCompleteListener { regRef->
            if (regRef.isSuccessful){
                Toast.makeText(context,"User Successfully Registered ", Toast.LENGTH_LONG).show()
                navController.navigate(ROUTE_TIPS)
            }else{
                _errorMessage.value = regRef.exception?.message

                Toast.makeText(context,"Database Error",Toast.LENGTH_LONG).show()
            }
        }
    }
    fun login(email: String, password: String, navController: NavController, context: Context){
        if(email.isBlank()|| password.isBlank()){
            Toast.makeText(context,"Email and password required",Toast.LENGTH_LONG).show()
            return
        }
        _isloading.value = true

        mAuth.signInWithEmailAndPassword(email,password )
            .addOnCompleteListener { task ->
                _isloading.value = false
                if (task.isSuccessful){
                    Toast.makeText(context,"User successfully logged in",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_TIPS)
                }
                else{
                    _errorMessage.value = task.exception?.message

                    Toast.makeText(context,"Log In Failed",Toast.LENGTH_LONG).show()
                }
            }

    }
    fun logout() {
        FirebaseAuth.getInstance().signOut()
    }




    fun changePassword(
        currentpass: String,
        newpass: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val user = FirebaseAuth.getInstance().currentUser
        val email = user?.email

        if (user != null && email != null) {
            val credential = EmailAuthProvider.getCredential(email, currentpass)
            user.reauthenticate(credential)
                .addOnSuccessListener {
                    user.updatePassword(newpass)
                        .addOnSuccessListener { onSuccess() }
                        .addOnFailureListener { e -> onError("Failed to change password: ${e.message}") }
                }
                .addOnFailureListener { e -> onError("Re-authentication failed: ${e.message}") }
        } else {
            onError("User not logged in.")
        }
    }}






