package com.capstone.sano.views.stroke

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.capstone.sano.R
import com.capstone.sano.databinding.ActivityStrokeBinding
import com.capstone.sano.models.Injection
import com.capstone.sano.models.Result
import com.capstone.sano.viewModels.ViewModelFactory

class StrokeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStrokeBinding
    private val viewModel: StrokeViewModel by viewModels {
        ViewModelFactory.useViewModelFactory {
            StrokeViewModel(Injection.provideRepository(this))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityStrokeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.materialToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.penyakit_stroke)

        binding.btnDetect.setOnClickListener { detect() }
    }

    @SuppressLint("SetTextI18n")
    private fun detect() {
        if (binding.etAge.text.isEmpty() && binding.etAgl.text.isEmpty() && binding.etBmi.text.isEmpty() && binding.etHp.text.isEmpty() && binding.etHd.text.isEmpty() && binding.etSs.text.isEmpty()){
            Toast.makeText(this, "Silahkan isi semua field!", Toast.LENGTH_SHORT).show()
        }
        else {
            val age = binding.etAge.text.toString().toInt()
            val agl = binding.etAgl.text.toString().toFloat()
            val bmi = binding.etBmi.text.toString().toFloat()
            val hp = binding.etHp.text.toString()
            val hd = binding.etHd.text.toString()
            val ss = binding.etSs.text.toString()

            val modifiedHp = if (hp.equals("Ya", ignoreCase = true) || hp.equals("Yes", ignoreCase = true)) 1 else 0
            val modifiedHd = if (hd.equals("Ya", ignoreCase = true) || hd.equals("Yes", ignoreCase = true)) 1 else 0
            val modifiedSs = if (ss.equals("Pernah", ignoreCase = true) || ss.equals("Ever", ignoreCase = true)) 1 else 0

            viewModel.strokeAnalyze(
                age, agl, bmi, modifiedHp, modifiedHd, modifiedSs
            ).observe(this){

                if (it is Result.Loading) {
                    showLoading(true)
                } else {
                    showLoading(false)
                    when (it) {
                        is Result.Success -> {
                            val data = it.data
                            binding.result.visibility = View.VISIBLE
                            binding.strokeRisk.visibility = View.VISIBLE
                            binding.resultLabel.visibility = View.VISIBLE
                            binding.resultSuggestion.visibility = View.VISIBLE
                            binding.strokeRisk.text = getString(R.string.stroke_risk) + data.strokeRisk.strokeRisk
                            binding.resultLabel.text = getString(R.string.label) + data.strokeRisk.label
                            binding.resultSuggestion.text = getString(R.string.suggestion) + data.strokeRisk.suggestion
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