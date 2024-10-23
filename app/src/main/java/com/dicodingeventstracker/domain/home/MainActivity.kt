package com.dicodingeventstracker.domain.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope
import com.dicodingeventstracker.R
import com.dicodingeventstracker.domain.viewmodels.SettingThemeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val themeViewModel: SettingThemeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Observe theme settings using Flow
        lifecycleScope.launch {
            themeViewModel.themeSetting.collect { isDarkModeActive ->
                AppCompatDelegate.setDefaultNightMode(
                    if (isDarkModeActive) AppCompatDelegate.MODE_NIGHT_YES
                    else AppCompatDelegate.MODE_NIGHT_NO
                )
            }
        }
    }
}