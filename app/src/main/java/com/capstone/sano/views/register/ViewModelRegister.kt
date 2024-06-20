package com.capstone.sano.views.register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.sano.models.Repository

class RegisterViewModel(private val Repository: Repository) : ViewModel() {
    fun registerUser(name: String, email: String, password: String, confPassword: String) =
        Repository.registerUser(name, email, password, confPassword).asLiveData()
}