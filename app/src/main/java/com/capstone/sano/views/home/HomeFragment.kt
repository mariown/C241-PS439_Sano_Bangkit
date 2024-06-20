package com.capstone.sano.views.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.capstone.sano.databinding.FragmentHomeBinding
import com.capstone.sano.views.diabetes.DiabetesActivity
import com.capstone.sano.views.jantung.JantungActivity
import com.capstone.sano.views.stroke.StrokeActivity

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnDetectDiabetes.setOnClickListener {
                Intent(requireContext(), DiabetesActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnDetectStroke.setOnClickListener {
                Intent(requireContext(), StrokeActivity::class.java).also {
                    startActivity(it)
                }
            }
            btnDetectJantung.setOnClickListener {
                Intent(requireContext(), JantungActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }
}