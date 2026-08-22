package com.dropdownstatusbar.app

import android.accessibilityservice.AccessibilityService

enum class PanelAction(val preferenceValue: String, val globalAction: Int) {
    NOTIFICATIONS("notifications", AccessibilityService.GLOBAL_ACTION_NOTIFICATIONS),
    QUICK_SETTINGS("quick_settings", AccessibilityService.GLOBAL_ACTION_QUICK_SETTINGS);

    companion object {
        fun fromPreference(value: String?): PanelAction =
            entries.firstOrNull { it.preferenceValue == value } ?: NOTIFICATIONS
    }
}
