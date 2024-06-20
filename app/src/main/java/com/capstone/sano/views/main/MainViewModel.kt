package com.capstone.sano.views.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.sano.models.Repository
import com.capstone.sano.models.store.UserModel

class MainViewModel(private val Repository: Repository) : ViewModel() {
    fun getSession(): LiveData<UserModel> {
        return Repository.getSession().asLiveData()
    }
}