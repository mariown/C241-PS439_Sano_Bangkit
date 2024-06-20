package com.capstone.sano.views.jantung

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.sano.R
import com.capstone.sano.databinding.ActivityJantungBinding
import com.capstone.sano.models.Injection
import com.capstone.sano.models.Result
import com.capstone.sano.viewModels.ViewModelFactory

class JantungActivity : AppCompatActivity() {
    private lateinit var binding: ActivityJantungBinding

    private val viewModel: JantungViewModel by viewModels {
        ViewModelFactory.useViewModelFactory {
            JantungViewModel(Injection.provideRepository(this))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityJantungBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.penyakit_jantung)

        binding.btnDetect.setOnClickListener { detect() }
    }

    @SuppressLint("SetTextI18n")
    private fun detect() {
        if (binding.etAge.text.isEmpty() && binding.etTro.text.isEmpty() && binding.etKcm.text.isEmpty() && binding.etGlu.text.isEmpty() && binding.etPh.text.isEmpty() && binding.etPl.text.isEmpty()){
            Toast.makeText(this, "Silahkan isi semua field!", Toast.LENGTH_SHORT).show()
        }
        else {
            viewModel.heartAnalyze(
                binding.etAge.text.toString().toInt(),
                binding.etTro.text.toString().toFloat(),
                binding.etKcm.text.toString().toFloat(),
                binding.etGlu.text.toString().toInt(),
                binding.etPh.text.toString().toFloat(),
                binding.etPl.text.toString().toFloat()
            ).observe(this){

                if (it is Result.Loading) {
                    showLoading(true)
                } else {
                    showLoading(false)
                    when (it) {
                        is Result.Success -> {
                            val data = it.data
                            binding.result.visibility = View.VISIBLE
                            binding.resultDisease.visibility = View.VISIBLE
                            binding.resultLabel.visibility = View.VISIBLE
                            binding.resultSuggestion.visibility = View.VISIBLE
                            binding.apply {
                                resultDisease.text = getString(R.string.heart_disease_risk) + data.heartDiseaseRisk.heartDiseaseRisk
                                resultLabel.text = getString(R.string.label) + data.heartDiseaseRisk.label
                                resultSuggestion.text = getString(R.string.suggestion) + data.heartDiseaseRisk.suggestion
                            }
                        }

                        is Result.Error -> {
                            Toast.makeText(this, it.error, Toast.LENGTH_LONG).show()
                        }

                        else -> {}
                    }
                }
            }
        }
    }
    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}