package com.jacknkiarie.signinui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jacknkiarie.signinui.models.SignInUI
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)

        email_only.setOnClickListener {
            SignInUI.Builder(this)
                .setSignInType(SignInUI.EMAIL_PASSWORD_FORM)
                .setTitle("Facebook")
                .setSubtitle("Connecting the world")
                .setPasswordLength(6)
                .setFingerprintSignInEnabled(false)
                .setPinSignInEnabled(false)
                .build()
        }

        email_with_pin.setOnClickListener {
            SignInUI.Builder(this)
                .setSignInType(SignInUI.EMAIL_PASSWORD_FORM)
                .setTitle("Facebook")
                .setSubtitle("Connecting the world")
                .setPasswordLength(6)
                .setPinSignInEnabled(true)
                .setFingerprintSignInEnabled(false)
                .build()
        }

        email_with_pin_and_fingerprint.setOnClickListener {
            SignInUI.Builder(this)
                .setSignInType(SignInUI.EMAIL_PASSWORD_FORM)
                .setTitle("Facebook")
                .setSubtitle("Connecting the world")
                .setPasswordLength(6)
                .setPinSignInEnabled(true)
                .setFingerprintSignInEnabled(true)
                .build()
        }



        fingerprint_only.setOnClickListener {
            SignInUI.Builder(this)
                .setSignInType(SignInUI.FINGERPRINT_FORM)
                .setTitle("Facebook")
                .setSubtitle("Connecting the world")
                .setPinSignInEnabled(false)
                .setEmailSignInEnabled(false)
                .build()
        }

        fingerprint_with_pin.setOnClickListener {
            SignInUI.Builder(this)
                .setSignInType(SignInUI.FINGERPRINT_FORM)
                .setTitle("Facebook")
                .setSubtitle("Connecting the world")
                .setPinSignInEnabled(true)
                .setEmailSignInEnabled(false)
                .build()
        }

        fingerprint_with_pin_and_email.setOnClickListener {
            SignInUI.Builder(this)
                .setSignInType(SignInUI.FINGERPRINT_FORM)
                .setTitle("Facebook")
                .setSubtitle("Connecting the world")
                .setPinSignInEnabled(true)
                .setEmailSignInEnabled(true)
                .build()
        }
    }
}
