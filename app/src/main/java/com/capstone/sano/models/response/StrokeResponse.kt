package com.capstone.sano.models.response

import com.google.gson.annotations.SerializedName

data class StrokeResponse(

	@field:SerializedName("stroke_risk")
	val strokeRisk: StrokeRisk
)

data class StrokeRisk(

	@field:SerializedName("stroke_risk")
	val strokeRisk: Any,

	@field:SerializedName("suggestion")
	val suggestion: String,

	@field:SerializedName("label")
	val label: String
)
