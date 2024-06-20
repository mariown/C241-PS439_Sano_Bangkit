package com.capstone.sano.views.jantung

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.sano.models.Repository

class JantungViewModel(private val repository: Repository) : ViewModel(){
    fun heartAnalyze(age: Int, tro: Float, kcm: Float, glu: Int, ph: Float, pl: Float) =
        repository.heartAnalyze(age, tro, kcm, glu, ph, pl).asLiveData()
}