package com.example.gitbook.network

import com.example.gitbook.data.RepoResponse
import com.example.gitbook.data.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("users/{username}")
    fun getUser(@Path("username") username:String): Call<User>


    @GET("users/{username}/repos")
    fun getUserRepo(@Path("username") username:String):Call<RepoResponse>
}