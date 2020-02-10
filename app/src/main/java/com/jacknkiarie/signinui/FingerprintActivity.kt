package com.jacknkiarie.signinui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jacknkiarie.signinui.models.SignInUI
import kotlinx.android.synthetic.main.activity_fingerprint.*

class FingerprintActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fingerprint)

        var fingerprintWrapper = FingerprintWrapper(this)
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

    }
}
