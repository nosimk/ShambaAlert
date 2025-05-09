package com.example.farmer.api

data class Location(
    val country : String,
    val lat : String,
    val localtime : String,
    val localtime_epoch:String,
    val lon: Double,
    val name:String,
    val region : String,
    val tz_id :String
)
