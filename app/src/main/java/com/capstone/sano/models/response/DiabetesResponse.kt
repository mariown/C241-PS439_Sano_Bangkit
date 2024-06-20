package com.capstone.sano.models.response

import com.google.gson.annotations.SerializedName

data class DiabetesResponse(

	@field:SerializedName("diabetes_risk")
	val diabetesRisk: DiabetesRisk
)

data class DiabetesRisk(

	@field:SerializedName("diabetes_risk")
	val diabetesRisk: Any,

	@field:SerializedName("suggestion")
	val suggestion: String,

	@field:SerializedName("label")
	val label: String
)
