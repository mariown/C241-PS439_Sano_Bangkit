package com.capstone.sano.views.stroke

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.sano.models.Repository

class StrokeViewModel(private val repository: Repository) : ViewModel(){
    fun strokeAnalyze(age: Int, avg: Float, bmi: Float, hyper: Int, heart: Int, smoking: Int) =
        repository.strokeAnalyze(age, avg, bmi, hyper, heart, smoking).asLiveData()
}