package com.capstone.sano.views.main

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.capstone.sano.Utils
import com.capstone.sano.Utils.setAppLocale
import com.capstone.sano.databinding.ActivityMainBinding
import com.capstone.sano.models.Injection
import com.capstone.sano.viewModels.ViewModelFactory
import com.capstone.sano.views.dashboard.DashboardActivity
import com.capstone.sano.views.login.LoginActivity
import com.capstone.sano.views.register.RegisterActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels {
        ViewModelFactory.useViewModelFactory {
            MainViewModel(Injection.provideRepository(this))
        }
    }

    override fun attachBaseContext(newBase: Context) {
        val pref = newBase.getSharedPreferences("main", MODE_PRIVATE)
        val localeValue = pref.getString("locale", "in")
        super.attachBaseContext(ContextWrapper(newBase.setAppLocale(localeValue ?: "in")))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val isNight = Utils.getIsDarkMode(this)
        if(isNight) AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        else  AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        setContentView(binding.root)

        viewModel.getSession().observe(this) { user ->
            if (user.isLogin) {
                startActivity(Intent(this, DashboardActivity::class.java))
                finish()
            }
        }

        binding.btnLogin.setOnClickListener {
            val intent = Intent(this@MainActivity, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        playAnimation()
    }

    private fun playAnimation() {
        val title = ObjectAnimator.ofFloat(binding.appWelcome, View.ALPHA, 1f).setDuration(1000)
        val motto = ObjectAnimator.ofFloat(binding.appMotto, View.ALPHA, 1f).setDuration(1000)
        val btnLogin = ObjectAnimator.ofFloat(binding.btnLogin, View.ALPHA, 1f).setDuration(1000)
        val btnRegister =
            ObjectAnimator.ofFloat(binding.btnRegister, View.ALPHA, 1f).setDuration(1000)

        val together = AnimatorSet().apply {
            playTogether(btnLogin, btnRegister)
        }
        AnimatorSet().apply {
            playSequentially(title, motto, together)
            start()
        }
    }
}