package com.example.gitbook.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val retrofitService:RetrofitService
    init{
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofitService = retrofit.create(RetrofitService::class.java)
    }
}