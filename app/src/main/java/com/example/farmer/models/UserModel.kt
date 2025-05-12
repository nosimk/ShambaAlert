package com.example.farmer.models

 data class UserModel(
     var fullname :String="",
     var phone : String="",
     var email :String="",
     var password: String="",
     var userId:String=""
 )

data class UserProfile(
    val fullname: String = "",
    val phone: String = "",
    val email: String ="",
    val profilePictureUrl : String ="",
    val userProfileId: String = "",
)
