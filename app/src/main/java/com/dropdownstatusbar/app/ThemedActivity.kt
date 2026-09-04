package com.dropdownstatusbar.app

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.Bundle
import android.view.View

abstract class ThemedActivity : Activity() {
    private lateinit var appliedAppearance: AppAppearance

    override fun attachBaseContext(newBase: Context) {
        val mode = AppPreferences(newBase).themeMode
        super.attachBaseContext(AppTheme.wrapContext(newBase, mode))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        appliedAppearance = AppPreferences(this).appearance
        setTheme(appliedAppearance.color.themeResource)
        super.onCreate(savedInstanceState)
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        updateSystemBars()
    }

    override fun onResume() {
        super.onResume()
        if (AppPreferences(this).appearance != appliedAppearance) recreate()
    }

    @Suppress("DEPRECATION")
    private fun updateSystemBars() {
        val isLight = resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK !=
            Configuration.UI_MODE_NIGHT_YES
        val barColor = getColor(R.color.page_background)
        window.statusBarColor = barColor
        window.navigationBarColor = barColor

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val lightBars = if (isLight) {
                android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or
                    android.view.WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
            } else {
                0
            }
            window.insetsController?.setSystemBarsAppearance(
                lightBars,
                android.view.WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS or
                    android.view.WindowInsetsController.APPEARANCE_LIGHT_NAVIGATION_BARS
            )
        } else {
            var flags = window.decorView.systemUiVisibility
            flags = flags and View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR.inv()
            flags = flags and View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR.inv()
            if (isLight) {
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                flags = flags or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
            }
            window.decorView.systemUiVisibility = flags
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            window.isNavigationBarContrastEnforced = false
        }
    }
}
