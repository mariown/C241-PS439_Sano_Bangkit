package com.capstone.sano.views.setting

import androidx.lifecycle.ViewModel
import com.capstone.sano.models.Repository

class SettingViewModel(private val Repository: Repository) : ViewModel() {
    fun logout() = Repository.logout()
}