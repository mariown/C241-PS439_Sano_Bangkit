package com.capstone.sano.models.store

data class UserModel(
    val email: String,
    val token: String,
    val isLogin: Boolean = false
)
