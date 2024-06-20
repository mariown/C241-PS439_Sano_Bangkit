package com.capstone.sano.models.response

import com.google.gson.annotations.SerializedName

data class HeartDiseaseResponse(

	@field:SerializedName("heart_disease_risk")
	val heartDiseaseRisk: HeartDisease
)

data class HeartDisease(

	@field:SerializedName("heart_disease_risk")
	val heartDiseaseRisk: Any,

	@field:SerializedName("suggestion")
	val suggestion: String,

	@field:SerializedName("label")
	val label: String
)
