package com.dropdownstatusbar.app

import android.accessibilityservice.AccessibilityService
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.accessibility.AccessibilityEvent

class PanelAccessibilityService : AccessibilityService() {
    override fun onServiceConnected() {
        instance = this
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) = Unit

    override fun onInterrupt() = Unit

    override fun onDestroy() {
        if (instance === this) instance = null
        super.onDestroy()
    }

    private fun execute(action: PanelAction): Boolean {
        val succeeded = performGlobalAction(action.globalAction)
        if (succeeded && AppPreferences(this).vibrationEnabled) {
            val vibrator = getSystemService(Vibrator::class.java)
            vibrator.vibrate(VibrationEffect.createOneShot(25L, VibrationEffect.DEFAULT_AMPLITUDE))
        }
        return succeeded
    }

    companion object {
        @Volatile
        private var instance: PanelAccessibilityService? = null

        fun execute(action: PanelAction): Boolean = instance?.execute(action) == true
    }
}
