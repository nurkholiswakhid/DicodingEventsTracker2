package com.dicodingeventstracker.domain.splashscreen

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.dicodingeventstracker.R
import com.dicodingeventstracker.domain.home.MainActivity
import java.util.Timer
import kotlin.concurrent.schedule

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Set status bar appearance based on theme
        setupStatusBar()

        // Navigate to MainActivity after delay
        setupNavigation()
    }

    private fun setupStatusBar() {
        val isDarkMode = resources.configuration.uiMode and
                Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES

        window.statusBarColor = if (isDarkMode) {
            getColor(R.color.black)
        } else {
            getColor(R.color.white)
        }

        // Get the WindowInsetsControllerCompat
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)

        // Configure the behavior of the status bars
        windowInsetsController.isAppearanceLightStatusBars = !isDarkMode
    }

    private fun setupNavigation() {
        Timer("splashGone", true).schedule(3000) {
            Intent(this@SplashScreenActivity, MainActivity::class.java).also { backToMain ->
                startActivity(backToMain)
                finish()
            }
        }
    }
}