package com.example.recipefinder

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://api.edamam.com/api/recipes/v2/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val edamamApiService: EdamamApiService by lazy {
        retrofit.create(EdamamApiService::class.java)
    }
}
