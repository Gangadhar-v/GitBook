package com.example.gitbook.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gitbook.data.RepoResponse
import com.example.gitbook.data.User
import com.example.gitbook.data.UserRepository

class UserViewModel: ViewModel() {

    val UserRepository = UserRepository()
    fun getUser(username:String): LiveData<User> {
        return UserRepository.getUser(username)
    }

    fun getUserRepos(username:String): LiveData<RepoResponse> {
        return UserRepository.getUserRepos(username)
    }
}