package com.capstone.sano.views.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.sano.models.Repository

class LoginViewModel(private val Repository: Repository) : ViewModel() {
    fun loginUser(email: String, password: String) =
        Repository.loginUser(email, password).asLiveData()
}