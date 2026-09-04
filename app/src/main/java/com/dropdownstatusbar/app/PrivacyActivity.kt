package com.dropdownstatusbar.app

import android.os.Bundle
import android.widget.Button

class PrivacyActivity : ThemedActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
        findViewById<Button>(R.id.close_privacy).setOnClickListener { finish() }
    }
}
