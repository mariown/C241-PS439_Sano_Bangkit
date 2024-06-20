package com.capstone.sano.models.response

import com.google.gson.annotations.SerializedName

data class RegisterResponse(
	@field:SerializedName("msg")
	val msg: String
)
