package com.capstone.sano.network

import com.capstone.sano.models.response.DiabetesResponse
import com.capstone.sano.models.response.HeartDiseaseResponse
import com.capstone.sano.models.LoginBody
import com.capstone.sano.models.response.LoginResponse
import com.capstone.sano.models.RegisterBody
import com.capstone.sano.models.response.RegisterResponse
import com.capstone.sano.models.response.StrokeResponse
import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiInterface {
    @POST("predict_diabetes")
    suspend fun predictDiabetes(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): DiabetesResponse

    @POST("predict_stroke")
    suspend fun predictStroke(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): StrokeResponse

    @POST("predict_heart_disease")
    suspend fun predictJantung(
        @Header("Authorization") token: String,
        @Body requestBody: RequestBody
    ): HeartDiseaseResponse

    @POST("login")
    suspend fun login(
        @Body requestBody: LoginBody
    ): LoginResponse

    @POST("users")
    suspend fun register(
        @Body requestBody: RegisterBody
    ): RegisterResponse
}
