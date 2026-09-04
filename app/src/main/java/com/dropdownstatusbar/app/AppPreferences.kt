package com.dropdownstatusbar.app

import android.content.Context

class AppPreferences(context: Context) {
    private val preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE)

    var defaultAction: PanelAction
        get() = PanelAction.fromPreference(preferences.getString(KEY_DEFAULT_ACTION, null))
        set(value) {
            preferences.edit().putString(KEY_DEFAULT_ACTION, value.preferenceValue).apply()
        }

    var vibrationEnabled: Boolean
        get() = preferences.getBoolean(KEY_VIBRATION, true)
        set(value) {
            preferences.edit().putBoolean(KEY_VIBRATION, value).apply()
        }

    var themeMode: AppThemeMode
        get() = AppThemeMode.fromPreference(preferences.getString(KEY_THEME_MODE, null))
        set(value) {
            preferences.edit().putString(KEY_THEME_MODE, value.preferenceValue).apply()
        }

    var themeColor: AppThemeColor
        get() = AppThemeColor.fromPreference(preferences.getString(KEY_THEME_COLOR, null))
        set(value) {
            preferences.edit().putString(KEY_THEME_COLOR, value.preferenceValue).apply()
        }

    val appearance: AppAppearance
        get() = AppAppearance(themeMode, themeColor)

    companion object {
        private const val FILE_NAME = "app_preferences"
        private const val KEY_DEFAULT_ACTION = "default_action"
        private const val KEY_VIBRATION = "vibration_enabled"
        private const val KEY_THEME_MODE = "theme_mode"
        private const val KEY_THEME_COLOR = "theme_color"
    }
}
