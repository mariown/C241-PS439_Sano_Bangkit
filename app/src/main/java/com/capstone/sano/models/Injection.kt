package com.capstone.sano.models

import android.content.Context
import com.capstone.sano.models.store.SettingPreference
import com.capstone.sano.models.store.dataStore
import com.capstone.sano.network.RetrofitClient

object Injection {
    fun provideRepository(context: Context): Repository {
        val pref = SettingPreference.getInstance(context.dataStore)
        val apiService = RetrofitClient.getApiService()
        return Repository.getInstance(apiService, pref)
    }
}