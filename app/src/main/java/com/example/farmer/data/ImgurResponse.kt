package com.example.farmer.data

data class ImgurResponse(
    val data: ImgurData,
    val success: Boolean,
    val status: Int
)

data class ImgurData(
    val id: String,
    val title: String?,
    val description: String?,
    val link: String
)

