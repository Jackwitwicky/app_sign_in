package com.jacknkiarie.signinui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.jacknkiarie.signinui.models.SignInUI
import java.util.concurrent.Executor

class FingerprintWrapper(val context: AppCompatActivity) {

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    init {
        setupFingerprintSensor()
    }

    fun setupFingerprintSensor() {
        executor = ContextCompat.getMainExecutor(context)
        biometricPrompt = BiometricPrompt(context, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(errorCode: Int,
                                                   errString: CharSequence) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(context,
                        "Authentication error: $errString", Toast.LENGTH_SHORT)
                        .show()
                    val fingerprintIntent = Intent()
                    fingerprintIntent.putExtra(SignInUI.PARAM_SIGN_IN_TYPE, SignInUI.FINGERPRINT_FORM)
                    context.setResult(SignInUI.RESULT_CANCEL, fingerprintIntent)
                    context.finish()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(context,
                        "Authentication succeeded!", Toast.LENGTH_SHORT)
                        .show()
                    val fingerprintIntent = Intent()
                    fingerprintIntent.putExtra(SignInUI.PARAM_SIGN_IN_TYPE, SignInUI.FINGERPRINT_FORM)
                    context.setResult(SignInUI.RESULT_OK, fingerprintIntent)
                    context.finish()
                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(context, "Authentication failed",
                        Toast.LENGTH_SHORT)
                        .show()
                    val fingerprintIntent = Intent()
                    fingerprintIntent.putExtra(SignInUI.PARAM_SIGN_IN_TYPE, SignInUI.FINGERPRINT_FORM)
                    context.setResult(SignInUI.RESULT_CANCEL, fingerprintIntent)
                    context.finish()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric login for my app")
            .setSubtitle("Log in using your fingerprint credential")
            .setNegativeButtonText("Use account password")
            .build()

    }

    fun checkFingerprintSupportAndAuthenticate() {
        val biometricManager = BiometricManager.from(context)
        when (biometricManager.canAuthenticate()) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                biometricPrompt.authenticate(promptInfo)
                Log.d("MY_APP_TAG", "App can authenticate using biometrics.")
            }
            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                Log.e("MY_APP_TAG", "No biometric features available on this device.")
                Toast.makeText(context, R.string.error_fingerprint_not_present, Toast.LENGTH_SHORT).show()
                val fingerprintIntent = Intent()
                fingerprintIntent.putExtra(SignInUI.PARAM_SIGN_IN_TYPE, SignInUI.FINGERPRINT_FORM)
                context.setResult(SignInUI.RESULT_CANCEL, fingerprintIntent)
                context.finish()
            }
            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                Log.e("MY_APP_TAG", "Biometric features are currently unavailable.")
                Toast.makeText(context, R.string.error_fingerprint_not_available, Toast.LENGTH_SHORT).show()
                val fingerprintIntent = Intent()
                fingerprintIntent.putExtra(SignInUI.PARAM_SIGN_IN_TYPE, SignInUI.FINGERPRINT_FORM)
                context.setResult(SignInUI.RESULT_CANCEL, fingerprintIntent)
                context.finish()
            }
            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                Log.e("MY_APP_TAG", "The user hasn't associated " +
                        "any biometric credentials with their account.")
                Toast.makeText(context, R.string.error_fingerprint_not_setup, Toast.LENGTH_SHORT).show()
                val fingerprintIntent = Intent()
                fingerprintIntent.putExtra(SignInUI.PARAM_SIGN_IN_TYPE, SignInUI.FINGERPRINT_FORM)
                context.setResult(SignInUI.RESULT_CANCEL, fingerprintIntent)
                context.finish()
            }
        }

    }
}