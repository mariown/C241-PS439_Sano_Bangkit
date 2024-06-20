package com.capstone.sano.views.diabetes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.sano.models.Repository

class DiabetesViewModel(private val repository: Repository) : ViewModel(){
    fun diabetesAnalyze(ffpg: Float, fpg: Float, age: Int, hdl: Float, ldl: Float, sbp: Float) =
        repository.diabetesAnalyze(ffpg, fpg, age, hdl, ldl, sbp).asLiveData()
}