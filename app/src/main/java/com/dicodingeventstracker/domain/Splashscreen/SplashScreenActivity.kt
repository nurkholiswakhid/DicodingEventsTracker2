package com.dicodingeventstracker.domain.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.dicodingeventstracker.R
import com.dicodingeventstracker.domain.home.MainActivity

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        setupStatusBar()
        setupNavigation()
    }

    private fun setupStatusBar() {
        try {
            val isDarkMode = resources.configuration.uiMode and
                    Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

            window?.apply {
                statusBarColor = getColor(
                    if (isDarkMode) R.color.black else R.color.white
                )

                // Menggunakan WindowCompat untuk mengatur status bar
                WindowCompat.getInsetsController(this, decorView).apply {
                    isAppearanceLightStatusBars = !isDarkMode
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupNavigation() {
        // Menggunakan Handler untuk delayed navigation yang lebih aman
        Handler(Looper.getMainLooper()).postDelayed({
            try {
                if (!isFinishing) {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, SPLASH_DELAY)
    }

    companion object {
        private const val SPLASH_DELAY = 3000L
    }
}