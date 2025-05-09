package com.example.farmer.network
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor


object RetrofitClient {
    private const val BASE_URL = "https://api.imgur.com/"
    const val CLIENT_ID = "Client-ID YOUR_CLIENT_ID"  // Replace with your Imgur Client ID

    val instance: ImgurApiService by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        retrofit.create(ImgurApiService::class.java)
    }
}
