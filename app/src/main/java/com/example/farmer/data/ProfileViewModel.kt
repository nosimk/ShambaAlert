package com.example.farmer.data
import android.content.Context
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.farmer.models.UserModel
import com.example.farmer.models.UserProfile
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import okhttp3.*
import org.json.JSONObject
import java.io.IOException






    class ProfileViewModel : ViewModel() {
        private val database = FirebaseDatabase.getInstance().reference
        private val auth = FirebaseAuth.getInstance()

        private val _userData = MutableStateFlow<UserModel?>(null)
        val userData: StateFlow<UserModel?> = _userData

        private val _profileImage = MutableStateFlow<UserProfile?>(null)
        val profileImage: StateFlow<UserProfile?> = _profileImage

        fun fetchUserData() {
            val uid = auth.currentUser?.uid ?: return
            database.child("Profiles").child(uid).get().addOnSuccessListener {
                _userData.value = it.getValue(UserModel::class.java)
            }
        }

        fun fetchProfileImage() {
            val uid = auth.currentUser?.uid ?: return
            database.child("profiles").child(uid).get().addOnSuccessListener {
                _profileImage.value = it.getValue(UserProfile::class.java)
            }
        }

        fun updateProfile(fullname: String, phone: String,email:String, profileUrl: String?) {
            val uid = auth.currentUser?.uid ?: return
            val updates = mapOf(
                "fullname" to fullname,
                "email" to email,
                "phone" to phone
            )
            database.child("users").child(uid).updateChildren(updates)
            profileUrl?.let {
                database.child("profiles").child(uid).setValue(UserProfile(it))
            }
        }

        fun uploadToImgur(context: Context, imageUri: Uri, onResult: (String?) -> Unit) {
            val clientId = "4d6df5f198d2241"

            val inputStream = context.contentResolver.openInputStream(imageUri)
            val imageBytes = inputStream?.readBytes()
            inputStream?.close()

            val requestBody = MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", Base64.encodeToString(imageBytes, Base64.DEFAULT))
                .build()

            val request = Request.Builder()
                .url("https://api.imgur.com/3/image")
                .addHeader("Authorization", "Client-ID $clientId")
                .post(requestBody)
                .build()

            OkHttpClient().newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    Log.e("ImgurUpload", "Upload failed: ${e.message}")
                    onResult(null)
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body?.string()
                    try {
                        val link = JSONObject(body ?: "").getJSONObject("data").getString("link")
                        Log.d("ImgurUpload", "Upload success: $link")
                        onResult(link)
                    } catch (e: Exception) {
                        Log.e("ImgurUpload", "Parse error: $e")
                        onResult(null)
                    }
                }
            })
    }}