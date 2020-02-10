package com.jacknkiarie.signinui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_email_password.*
import android.util.Log
import android.widget.Toast
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.jacknkiarie.signinui.models.FormValidator
import java.util.concurrent.Executor

class EmailPasswordActivity : AppCompatActivity() {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_password)

        email_password_pin_login.setOnClickListener {
            val pinLoginIntent = Intent(this@EmailPasswordActivity, PinActivity::class.java)
            startActivity(pinLoginIntent)
        }

        email_password_fingerprint_login.setOnClickListener {
            checkFingerprintSupport()
        }

        email_password_login_button.setOnClickListener{
            if(validateFields()) {
                Toast.makeText(this@EmailPasswordActivity, "Okay", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@EmailPasswordActivity, "Not okay", Toast.LENGTH_SHORT).show()
            }
        }

        setupFingerprintSensor()
    }

    private fun validateFields() : Boolean {
        val formValidator = FormValidator(this@EmailPasswordActivity)

        val emailValidator = formValidator.validateEmail(email_password_email_field.text.toString())
        if (!emailValidator.isFormValid()) {
            email_password_email_field.error = emailValidator.responseMessage
        }

        val passwordValidator = formValidator.validatePassword(email_password_password_field.text.toString())
        if(!passwordValidator.isFormValid()) {
            email_password_password_field.error = passwordValidator.responseMessage
        }

        return formValidator.isFormValid
    }

    fun checkFingerprintSupport() {
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                biometricPrompt.authenticate(promptInfo)
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Log.e("MY_APP_TAG", "No biometric features available on this device.")
                Toast.makeText(this@EmailPasswordActivity, R.string.error_fingerprint_not_present, Toast.LENGTH_SHORT).show()
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
                Toast.makeText(this@EmailPasswordActivity, R.string.error_fingerprint_not_available, Toast.LENGTH_SHORT).show()
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Log.e("MY_APP_TAG", "The user hasn't associated " +
                        "any biometric credentials with their account.")
                Toast.makeText(this@EmailPasswordActivity, R.string.error_fingerprint_not_setup, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun setupFingerprintSensor() {
        executor = ContextCompat.getMainExecutor(this)
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(applicationContext,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your fingerprint credential")
            .setNegativeButtonText("Use account password")
            .build()

    }
}