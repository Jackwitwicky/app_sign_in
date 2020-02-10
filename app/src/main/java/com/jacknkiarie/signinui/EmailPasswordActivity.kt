package com.jacknkiarie.signinui

import android.content.Intent

import android.os.Bundle
import kotlinx.android.synthetic.main.activity_email_password.*
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.jacknkiarie.signinui.models.FormValidator
import com.jacknkiarie.signinui.models.SignInUI

class EmailPasswordActivity : AppCompatActivity() {
    private var passwordLength: Int = SignInUI.DEFAULT_PASSWORD_LENGTH


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_password)

        val fingerprintWrapper = FingerprintWrapper(this)

        email_password_pin_login.setOnClickListener {
            val pinLoginIntent = Intent(this@EmailPasswordActivity, PinActivity::class.java)
            startActivity(pinLoginIntent)
        }

        email_password_fingerprint_login.setOnClickListener {
            fingerprintWrapper.checkFingerprintSupportAndAuthenticate()
        }

        email_password_login_button.setOnClickListener{
            if(validateFields()) {
                Toast.makeText(this@EmailPasswordActivity, "Okay", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this@EmailPasswordActivity, "Not okay", Toast.LENGTH_SHORT).show()
            }
        }

        setupUI()
    }

    private fun setupUI() {
        val isPinEnabled = intent.getBooleanExtra(SignInUI.EXTRA_IS_PIN_ENABLED, false)
        val isFingerprintEnabled = intent.getBooleanExtra(SignInUI.EXTRA_IS_FINGEPRINT_ENABLED, false)
        val title = intent.getStringExtra(SignInUI.EXTRA_TITLE)
        val subtitle = intent.getStringExtra(SignInUI.EXTRA_SUBTITLE)
        passwordLength = intent.getIntExtra(SignInUI.EXTRA_PASSWORD_LENGTH, SignInUI.DEFAULT_PASSWORD_LENGTH)

        if(title != null && title.isNotEmpty()) {
            email_password_welcome.text = title
        }

        if(subtitle != null && subtitle.isNotEmpty()) {
            email_password_intro.text = subtitle
        }

        if (!isPinEnabled) {
            email_password_pin_login.visibility = View.GONE
        }

        if (!isFingerprintEnabled) {
            email_password_fingerprint_login.visibility = View.GONE
        }
    }

    private fun validateFields() : Boolean {
        val formValidator = FormValidator(this@EmailPasswordActivity)

        val emailValidator = formValidator.validateEmail(email_password_email_field.text.toString())
        if (!emailValidator.isFormValid()) {
            email_password_email_field.error = emailValidator.responseMessage
        }

        val passwordValidator = formValidator.validatePassword(email_password_password_field.text.toString(), passwordLength)
        if(!passwordValidator.isFormValid()) {
            email_password_password_field.error = passwordValidator.responseMessage
        }

        return formValidator.isFormValid
    }
}
