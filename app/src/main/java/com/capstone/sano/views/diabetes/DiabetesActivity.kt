package com.capstone.sano.views.diabetes

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.sano.R
import com.capstone.sano.databinding.ActivityDiabetesBinding
import com.capstone.sano.models.Injection
import com.capstone.sano.models.Result
import com.capstone.sano.viewModels.ViewModelFactory

class DiabetesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiabetesBinding
    private val viewModel: DiabetesViewModel by viewModels {
        ViewModelFactory.useViewModelFactory {
            DiabetesViewModel(Injection.provideRepository(this))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDiabetesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.penyakit_diabetes)

        binding.btnDetect.setOnClickListener { detect() }
    }

    @SuppressLint("SetTextI18n")
    private fun detect() {
        if (binding.etFfpg.text.isEmpty() && binding.etFpg.text.isEmpty() && binding.etAge.text.isEmpty() && binding.etHdl.text.isEmpty() && binding.etLdl.text.isEmpty() && binding.etSbp.text.isEmpty()){
            Toast.makeText(this, "Silahkan isi semua field!", Toast.LENGTH_SHORT).show()
        }
        else {
            viewModel.diabetesAnalyze(
                binding.etFfpg.text.toString().toFloat(),
                binding.etFpg.text.toString().toFloat(),
                binding.etAge.text.toString().toInt(),
                binding.etHdl.text.toString().toFloat(),
                binding.etLdl.text.toString().toFloat(),
                binding.etSbp.text.toString().toFloat()
            ).observe(this){

                if (it is Result.Loading) {
                    showLoading(true)
                } else {
                    showLoading(false)
                    when (it) {
                        is Result.Success -> {
                            val data = it.data
                            binding.result.visibility = View.VISIBLE
                            binding.diabetesRisk.visibility = View.VISIBLE
                            binding.resultLabel.visibility = View.VISIBLE
                            binding.resultSuggestion.visibility = View.VISIBLE
                            binding.apply {
                                diabetesRisk.text = getString(R.string.diabetes_risk) + data.diabetesRisk.diabetesRisk.toString()
                                resultLabel.text = getString(R.string.label) + data.diabetesRisk.label
                                resultSuggestion.text = getString(R.string.suggestion)  + data.diabetesRisk.suggestion
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