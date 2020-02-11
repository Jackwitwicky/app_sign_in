package com.jacknkiarie.signinui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.jacknkiarie.signinui.models.SignInUI
import kotlinx.android.synthetic.main.activity_fingerprint.*

class FingerprintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fingerprint)

        val fingerprintWrapper = FingerprintWrapper(this)
        fingerprintWrapper.checkFingerprintSupportAndAuthenticate()

        fingerprint_prompt_button.setOnClickListener {
            fingerprintWrapper.checkFingerprintSupportAndAuthenticate()
        }

        fingerprint_pin_login.setOnClickListener {
            val pinIntent = Intent(this@FingerprintActivity, PinActivity::class.java)
            pinIntent.putExtra(
                SignInUI.EXTRA_IS_FINGEPRINT_ENABLED,
                true
            )
            startActivity(pinIntent)
            finish()
        }

        fingerprint_password_login.setOnClickListener {
            val emailPasswordIntent = Intent(this@FingerprintActivity, EmailPasswordActivity::class.java)
            emailPasswordIntent.putExtra(
                SignInUI.EXTRA_IS_FINGEPRINT_ENABLED,
                true
            )
            startActivity(emailPasswordIntent)
            finish()
        }

        setupUI()
    }

    private fun setupUI() {
        val isEmailEnabled = intent.getBooleanExtra(SignInUI.EXTRA_IS_EMAIL_ENABLED, false)
        val isPinEnabled = intent.getBooleanExtra(SignInUI.EXTRA_IS_PIN_ENABLED, false)
        val title = intent.getStringExtra(SignInUI.EXTRA_TITLE)
        val subtitle = intent.getStringExtra(SignInUI.EXTRA_SUBTITLE)

        if(title != null && title.isNotEmpty()) {
            fingerprint_welcome.text = title
        }

        if(subtitle != null && subtitle.isNotEmpty()) {
            fingerprint_intro.text = subtitle
        }

        if (!isPinEnabled) {
            fingerprint_pin_login.visibility = View.GONE
        }

        if (!isEmailEnabled) {
            fingerprint_password_login.visibility = View.GONE
        }
    }
}
