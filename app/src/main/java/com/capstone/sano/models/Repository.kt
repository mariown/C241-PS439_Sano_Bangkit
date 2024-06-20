package com.capstone.sano.models

import android.util.Log
import com.capstone.sano.models.response.DiabetesResponse
import com.capstone.sano.models.response.HeartDiseaseResponse
import com.capstone.sano.models.response.StrokeResponse
import com.capstone.sano.models.store.SettingPreference
import com.capstone.sano.models.store.UserModel
import com.capstone.sano.network.ApiInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody

class Repository private constructor(
    private val apiService: ApiInterface,
    private val pref: SettingPreference
){
    fun loginUser(email: String, password: String): Flow<Result<String>> = flow<Result<String>> {
        emit(Result.Loading)
        try {
            val response = apiService.login(LoginBody(email, password))
            pref.saveAccessToken(response.accessToken)
            pref.saveSession(
                user = UserModel(
                    email,response.accessToken
                )
            )
            emit(Result.Success("Login Success"))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
            Log.e("Error Login", "${e.message}", e)
        }
    }

    fun registerUser(name: String, email: String, password: String, confPassword: String): Flow<Result<String>> = flow<Result<String>> {
        emit(Result.Loading)
        try {
            val response = apiService.register(RegisterBody(name, email, password, confPassword))
            emit(Result.Success(response.msg))
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

    fun heartAnalyze(age: Int, tro: Float, kcm: Float, glu: Int, ph: Float, pl: Float) = flow<Result<HeartDiseaseResponse>> {
        emit(Result.Loading)
        try {
            val token = "Bearer ${pref.getAccessToken()}"
            val json = """
            {
                "age": $age,
                "troponin": $tro,
                "kcm": $kcm,
                "glucose": $glu,
                "pressureheight": $ph,
                "presurelow": $pl 
            }
            """.trimIndent()
            val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
            val response = apiService.predictJantung(token, requestBody)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error occurred"))
            Log.e("Error heart", e.message ?: "Unknown error occurred")
        }
    }

    fun strokeAnalyze(age: Int, avg: Float, bmi: Float, hyper: Int, heart: Int, smoking: Int) = flow<Result<StrokeResponse>> {
        emit(Result.Loading)
        try {
            val token = "Bearer ${pref.getAccessToken()}"
            val json = """
            {
                "age": $age,
                "avg_glucose_level": $avg,
                "bmi": $bmi,
                "hypertension": $hyper,
                "heart_disease": $heart,
                "smoking_status": $smoking
            }
            """.trimIndent()
            val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
            val response = apiService.predictStroke(token, requestBody)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error occurred"))
            Log.e("Error Stroke", e.message ?: "Unknown error occurred")
        }
    }

    fun diabetesAnalyze(ffpg: Float, fpg: Float, age: Int, hdl: Float, ldl: Float, sbp: Float) = flow<Result<DiabetesResponse>> {
        emit(Result.Loading)
        try {
            val token = "Bearer ${pref.getAccessToken()}"
            val json = """
            {
                "ffpg": $ffpg,
                "fpg": $fpg,
                "age": $age,
                "hdl": $hdl,
                "ldl": $ldl,
                "sbp": $sbp
            }
            """.trimIndent()
            val requestBody = json.toRequestBody("application/json".toMediaTypeOrNull())
            val response = apiService.predictDiabetes(token, requestBody)
            emit(Result.Success(response))
        } catch (e: Exception) {
            emit(Result.Error(e.message ?: "Unknown error occurred"))
            Log.e("Error Stroke", e.message ?: "Unknown error occurred")
        }
    }

    fun getSession(): Flow<UserModel> {
        return pref.getSession()
    }

    fun logout() = runBlocking {
        pref.saveAccessToken("")
        pref.logout()
    }

    companion object {
        @Volatile
        private var instance: Repository? = null
        fun getInstance(
            apiService: ApiInterface,
            pref: SettingPreference
        ): Repository =
            instance ?: synchronized(this) {
                instance ?: Repository(apiService, pref)
            }.also { instance = it }
    }
}