package com.dropdownstatusbar.app

import android.app.Activity
import android.os.Bundle
import android.widget.Button

class PrivacyActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_privacy)
        findViewById<Button>(R.id.close_privacy).setOnClickListener { finish() }
    }
}
