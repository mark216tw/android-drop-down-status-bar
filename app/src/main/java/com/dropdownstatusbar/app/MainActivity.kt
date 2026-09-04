package com.dropdownstatusbar.app

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.widget.Button
import android.widget.RadioButton
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast

class MainActivity : ThemedActivity() {
    private lateinit var preferences: AppPreferences
    private lateinit var statusText: TextView
    private lateinit var enableButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = AppPreferences(this)

        statusText = findViewById(R.id.service_status)
        enableButton = findViewById(R.id.enable_service)
        val notificationsOption = findViewById<RadioButton>(R.id.action_notifications)
        val quickSettingsOption = findViewById<RadioButton>(R.id.action_quick_settings)
        val vibrationSwitch = findViewById<Switch>(R.id.vibration_switch)

        findViewById<android.view.View>(R.id.open_settings).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }

        when (preferences.defaultAction) {
            PanelAction.NOTIFICATIONS -> notificationsOption.isChecked = true
            PanelAction.QUICK_SETTINGS -> quickSettingsOption.isChecked = true
        }
        vibrationSwitch.isChecked = preferences.vibrationEnabled

        notificationsOption.setOnCheckedChangeListener { _, checked ->
            if (checked) preferences.defaultAction = PanelAction.NOTIFICATIONS
        }
        quickSettingsOption.setOnCheckedChangeListener { _, checked ->
            if (checked) preferences.defaultAction = PanelAction.QUICK_SETTINGS
        }
        vibrationSwitch.setOnCheckedChangeListener { _, checked ->
            preferences.vibrationEnabled = checked
        }

        enableButton.setOnClickListener {
            if (AccessibilityState.isServiceEnabled(this)) {
                openAccessibilitySettings()
            } else {
                showAccessibilityDisclosure()
            }
        }
        findViewById<Button>(R.id.test_notifications).setOnClickListener {
            testAction(PanelAction.NOTIFICATIONS)
        }
        findViewById<Button>(R.id.test_quick_settings).setOnClickListener {
            testAction(PanelAction.QUICK_SETTINGS)
        }
        findViewById<Button>(R.id.open_privacy).setOnClickListener {
            startActivity(Intent(this, PrivacyActivity::class.java))
        }

        if (savedInstanceState == null && intent.hasExtra(EXTRA_SERVICE_REQUIRED)) {
            val message = if (intent.getBooleanExtra(EXTRA_SERVICE_REQUIRED, false)) {
                R.string.service_required_message
            } else {
                R.string.service_not_ready_message
            }
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()
            intent.removeExtra(EXTRA_SERVICE_REQUIRED)
        }
    }

    override fun onResume() {
        super.onResume()
        updateServiceStatus()
    }

    private fun updateServiceStatus() {
        val enabled = AccessibilityState.isServiceEnabled(this)
        statusText.setText(if (enabled) R.string.service_enabled else R.string.service_disabled)
        statusText.setTextColor(
            getColor(if (enabled) R.color.status_enabled_text else R.color.status_disabled_text)
        )
        statusText.setBackgroundResource(
            if (enabled) R.drawable.bg_status_enabled else R.drawable.bg_status_disabled
        )
        enableButton.setText(if (enabled) R.string.manage_service else R.string.enable_service)
    }

    private fun showAccessibilityDisclosure() {
        AlertDialog.Builder(this)
            .setTitle(R.string.accessibility_disclosure_title)
            .setMessage(R.string.accessibility_disclosure)
            .setNegativeButton(R.string.cancel, null)
            .setPositiveButton(R.string.continue_label) { _, _ -> openAccessibilitySettings() }
            .show()
    }

    private fun openAccessibilitySettings() {
        startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
    }

    private fun testAction(action: PanelAction) {
        if (!AccessibilityState.isServiceEnabled(this)) {
            Toast.makeText(this, R.string.service_required_message, Toast.LENGTH_LONG).show()
            showAccessibilityDisclosure()
            return
        }
        if (!PanelAccessibilityService.execute(action)) {
            Toast.makeText(this, R.string.service_not_ready_message, Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        const val EXTRA_SERVICE_REQUIRED = "service_required"
    }
}
