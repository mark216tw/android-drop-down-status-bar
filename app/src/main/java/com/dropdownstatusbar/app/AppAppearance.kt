package com.dropdownstatusbar.app

import android.content.Context
import android.content.res.Configuration

enum class AppThemeMode(val preferenceValue: String) {
    SYSTEM("system"),
    LIGHT("light"),
    DARK("dark");

    companion object {
        fun fromPreference(value: String?): AppThemeMode =
            entries.firstOrNull { it.preferenceValue == value } ?: SYSTEM
    }
}

enum class AppThemeColor(val preferenceValue: String, val themeResource: Int) {
    BLUE("blue", R.style.Theme_DropDownStatusBar),
    PINK("pink", R.style.Theme_DropDownStatusBar_Pink),
    PURPLE("purple", R.style.Theme_DropDownStatusBar_Purple),
    TEAL("teal", R.style.Theme_DropDownStatusBar_Teal),
    ORANGE("orange", R.style.Theme_DropDownStatusBar_Orange),
    LIME("lime", R.style.Theme_DropDownStatusBar_Lime);

    companion object {
        fun fromPreference(value: String?): AppThemeColor =
            entries.firstOrNull { it.preferenceValue == value } ?: BLUE
    }
}

data class AppAppearance(
    val mode: AppThemeMode,
    val color: AppThemeColor
)

object AppTheme {
    fun wrapContext(context: Context, mode: AppThemeMode): Context {
        if (mode == AppThemeMode.SYSTEM) return context

        val configuration = Configuration(context.resources.configuration)
        val nightMode = when (mode) {
            AppThemeMode.LIGHT -> Configuration.UI_MODE_NIGHT_NO
            AppThemeMode.DARK -> Configuration.UI_MODE_NIGHT_YES
            AppThemeMode.SYSTEM -> return context
        }
        configuration.uiMode =
            (configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK.inv()) or nightMode
        return context.createConfigurationContext(configuration)
    }
}
