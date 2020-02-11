package com.jacknkiarie.signinui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.jacknkiarie.signinui.models.SignInUI
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {

    private var TAG = TestActivity::class.java.simpleName

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



        pin_only.setOnClickListener {
            SignInUI.Builder(this)
                .setSignInType(SignInUI.PIN_FORM)
                .setTitle("Facebook")
                .setSubtitle("Connecting the world")
                .setFingerprintSignInEnabled(true)
                .setEmailSignInEnabled(true)
                .setPinAsHidden(true)
                .setPinLength(4)
                .build()
        }

        pin_with_email.setOnClickListener {
            SignInUI.Builder(this)
                .setSignInType(SignInUI.PIN_FORM)
                .setTitle("Facebook")
                .setSubtitle("Connecting the world")
                .setFingerprintSignInEnabled(true)
                .setEmailSignInEnabled(true)
                .setPinAsHidden(true)
                .setPinLength(4)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == SignInUI.REQUEST_CODE) {
            if (resultCode == SignInUI.RESULT_OK) {
                Toast.makeText(this@TestActivity, "Everything went well", Toast.LENGTH_SHORT).show()
                val signInType = data!!.getStringExtra(SignInUI.PARAM_SIGN_IN_TYPE)
                Log.d(TAG, signInType)
                Log.d(TAG, data!!.getStringExtra(SignInUI.PARAM_EMAIL) + "")
                Log.d(TAG, data!!.getStringExtra(SignInUI.PARAM_PASSWORD) + "")
                Log.d(TAG, data!!.getStringExtra(SignInUI.PARAM_PIN) + "")
                Log.d("TEST_ACTIVITY", data.toString())
                // jack@gmail.com
            }
            else {
                Toast.makeText(this@TestActivity, "User cancelled", Toast.LENGTH_SHORT).show()
                Log.d("TEST_ACTIVITY", data.toString())
            }
        }
    }
}
