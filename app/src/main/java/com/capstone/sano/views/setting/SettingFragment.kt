package com.capstone.sano.views.setting

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.capstone.sano.Utils
import com.capstone.sano.Utils.setAppLocale
import com.capstone.sano.databinding.FragmentSettingBinding
import com.capstone.sano.models.Injection
import com.capstone.sano.viewModels.ViewModelFactory
import com.capstone.sano.views.login.LoginActivity
import com.capstone.sano.views.main.MainActivity

class SettingFragment : Fragment() {
    private lateinit var binding: FragmentSettingBinding
    private val viewModel: SettingViewModel by viewModels {
        ViewModelFactory.useViewModelFactory {
            SettingViewModel(Injection.provideRepository(requireActivity()))
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSettingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnNightMode.setOnClickListener {
            val isNightMode = Utils.getIsDarkMode(requireContext())
            Utils.setIsDarkMode(requireContext(), !isNightMode)
        }
        binding.btnLanguage.setOnClickListener {
            val pref = requireActivity().getSharedPreferences("main", AppCompatActivity.MODE_PRIVATE)
            var localeValue = pref.getString("locale", "in")

            localeValue = if(localeValue == "in") {
                "en"
            } else {
                "in"
            }

            pref.edit().putString("locale", localeValue).apply()
            requireContext().setAppLocale(localeValue)

            Intent(requireContext(), MainActivity::class.java).also {
                startActivity(it)
            }
            requireActivity().finish()
        }
        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            val intent = Intent(activity, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}