package com.capstone.sano.models

data class RegisterBody(
    val name: String,
    val email: String,
    val password: String,
    val confPassword: String,
)