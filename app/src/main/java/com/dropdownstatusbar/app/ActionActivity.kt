package com.dropdownstatusbar.app

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class ActionActivity : Activity() {
    private val handler = Handler(Looper.getMainLooper())
    private var attempt = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!AccessibilityState.isServiceEnabled(this)) {
            openSettings(serviceRequired = true)
            return
        }

        executeWhenReady(resolveAction(intent))
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }

    private fun executeWhenReady(action: PanelAction) {
        if (PanelAccessibilityService.execute(action)) {
            finish()
            return
        }

        attempt++
        if (attempt < MAX_ATTEMPTS) {
            handler.postDelayed({ executeWhenReady(action) }, RETRY_DELAY_MS)
        } else {
            openSettings(serviceRequired = false)
        }
    }

    private fun resolveAction(intent: Intent): PanelAction = when (intent.action) {
        ACTION_NOTIFICATIONS -> PanelAction.NOTIFICATIONS
        ACTION_QUICK_SETTINGS -> PanelAction.QUICK_SETTINGS
        else -> AppPreferences(this).defaultAction
    }

    private fun openSettings(serviceRequired: Boolean) {
        startActivity(
            Intent(this, MainActivity::class.java)
                .putExtra(MainActivity.EXTRA_SERVICE_REQUIRED, serviceRequired)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP)
        )
        finish()
    }

    companion object {
        const val ACTION_NOTIFICATIONS = "com.dropdownstatusbar.app.action.NOTIFICATIONS"
        const val ACTION_QUICK_SETTINGS = "com.dropdownstatusbar.app.action.QUICK_SETTINGS"
        private const val MAX_ATTEMPTS = 6
        private const val RETRY_DELAY_MS = 100L

        fun intent(context: Context, action: PanelAction): Intent =
            Intent(context, ActionActivity::class.java).setAction(
                if (action == PanelAction.NOTIFICATIONS) ACTION_NOTIFICATIONS else ACTION_QUICK_SETTINGS
            )
    }
}
