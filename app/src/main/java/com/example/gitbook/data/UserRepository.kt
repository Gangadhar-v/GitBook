package com.example.gitbook.data

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gitbook.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository {

    var user = MutableLiveData<User>()
    var repos = MutableLiveData<RepoResponse>()

    fun getUser(username: String): LiveData<User> {
        RetrofitInstance.retrofitService.getUser(username).enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.code() == 200 && response.body() != null) {
                    user.value = response.body()

                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {

                Handler(Looper.getMainLooper()).postDelayed({
                    call.clone().enqueue(this)
                }, 3000)

            }
        })

        return user
    }


    fun getUserRepos(username: String): LiveData<RepoResponse> {
        RetrofitInstance.retrofitService.getUserRepo(username)
            .enqueue(object : Callback<RepoResponse> {
                override fun onResponse(
                    call: Call<RepoResponse>,
                    response: Response<RepoResponse>
                ) {
                    if (response.isSuccessful) {
                        repos.value = response.body()
                    }
                }

                override fun onFailure(call: Call<RepoResponse>, t: Throwable) {


                    Handler(Looper.getMainLooper()).postDelayed({
                        call.clone().enqueue(this)
                    }, 3000)
                }

            })
        return repos
    }
}