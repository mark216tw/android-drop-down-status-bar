package com.dropdownstatusbar.app

import android.os.Bundle
import android.widget.RadioGroup
import android.view.View

class SettingsActivity : ThemedActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val preferences = AppPreferences(this)
        findViewById<View>(R.id.close_settings).setOnClickListener { finish() }

        val modeOptions = mapOf(
            R.id.theme_mode_system to AppThemeMode.SYSTEM,
            R.id.theme_mode_light to AppThemeMode.LIGHT,
            R.id.theme_mode_dark to AppThemeMode.DARK
        )
        val modeGroup = findViewById<RadioGroup>(R.id.theme_mode_group)
        modeGroup.check(modeOptions.entries.first { it.value == preferences.themeMode }.key)
        modeGroup.setOnCheckedChangeListener { _, checkedId ->
            val selected = modeOptions[checkedId] ?: return@setOnCheckedChangeListener
            if (selected != preferences.themeMode) {
                preferences.themeMode = selected
                recreate()
            }
        }

        val colorOptions = mapOf(
            R.id.theme_color_blue to AppThemeColor.BLUE,
            R.id.theme_color_pink to AppThemeColor.PINK,
            R.id.theme_color_purple to AppThemeColor.PURPLE,
            R.id.theme_color_teal to AppThemeColor.TEAL,
            R.id.theme_color_orange to AppThemeColor.ORANGE,
            R.id.theme_color_lime to AppThemeColor.LIME
        )
        val colorGroup = findViewById<RadioGroup>(R.id.theme_color_group)
        colorGroup.check(colorOptions.entries.first { it.value == preferences.themeColor }.key)
        colorGroup.setOnCheckedChangeListener { _, checkedId ->
            val selected = colorOptions[checkedId] ?: return@setOnCheckedChangeListener
            if (selected != preferences.themeColor) {
                preferences.themeColor = selected
                recreate()
            }
        }
    }
}
