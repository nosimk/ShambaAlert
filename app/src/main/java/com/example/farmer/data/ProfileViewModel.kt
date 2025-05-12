package com.example.farmer.data

import android.app.AlertDialog
import android.app.Application
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.farmer.models.UserModel
import com.example.farmer.models.UserProfile
import com.example.farmer.navigation.*
import com.example.farmer.network.ImgurApiService
import com.example.farmer.network.RetrofitClient
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import androidx.lifecycle.AndroidViewModel


class ProfileViewModel:ViewModel () {
    private val database = FirebaseDatabase.getInstance().reference.child("Students")

    private fun getImgurService(): ImgurApiService {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.imgur.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()

        return retrofit.create(ImgurApiService::class.java)
    }

    private fun getFileFromUri(context: Context, uri: Uri):
            File? {
        return try {
            val inputStream = context.contentResolver
                .openInputStream(uri)
            val file = File.createTempFile("temp_image", ".jpg", context.cacheDir)
            file.outputStream().use { output ->
                inputStream?.copyTo(output)
            }
            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun updateStudent(context: Context, navController: NavController,
                      fullname: String, email: String,
                      phone: String, userProfileId: String){
        val databaseReference = FirebaseDatabase.getInstance()
            .getReference("Updated/$userProfileId")
        val updatedStudent = UserProfile(fullname,email,
            phone, userProfileId)

        databaseReference.setValue(updatedStudent)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){

                    Toast.makeText(context,"Student Updated Successfully",Toast.LENGTH_LONG).show()
                    navController.navigate(ROUTE_SETTINGS)
                }else{

                    Toast.makeText(context,"Student update failed",Toast.LENGTH_LONG).show()
                }
            }
    }
}