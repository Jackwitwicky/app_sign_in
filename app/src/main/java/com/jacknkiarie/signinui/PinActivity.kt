package com.jacknkiarie.signinui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.Toast
import com.jacknkiarie.signinui.models.FormValidator
import com.jacknkiarie.signinui.models.SignInUI
import kotlinx.android.synthetic.main.activity_email_password.*
import kotlinx.android.synthetic.main.activity_pin.*

class PinActivity : AppCompatActivity() {

    var currentPinInput = ""
    var initpin = ""
    private var pinLength = SignInUI.DEFAULT_PIN_LENGTH

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)
        val fingerprintWrapper = FingerprintWrapper(this)

        pin_fingerprint_login.setOnClickListener{
            fingerprintWrapper.checkFingerprintSupportAndAuthenticate()
        }

        pin_check_btn.setOnClickListener{
            if (validateFields()) {
                val pinIntent = Intent()
                pinIntent.putExtra(SignInUI.PARAM_SIGN_IN_TYPE, SignInUI.PIN_FORM)
                pinIntent.putExtra(SignInUI.PARAM_PIN, pin_verification_code.text.toString())
                setResult(SignInUI.RESULT_OK, pinIntent)
                finish()
            }
        }

        setupUI()
    }

    private fun setupUI() {
        val isEmailEnabled = intent.getBooleanExtra(SignInUI.EXTRA_IS_EMAIL_ENABLED, false)
        val isFingerprintEnabled = intent.getBooleanExtra(SignInUI.EXTRA_IS_FINGEPRINT_ENABLED, false)
        pinLength = intent.getIntExtra(SignInUI.EXTRA_PIN_LENGTH, SignInUI.DEFAULT_PIN_LENGTH)
        val isPinHidden = intent.getBooleanExtra(SignInUI.EXTRA_IS_PIN_HIDDEN, true)

        if (!isEmailEnabled) {
            pin_email_login.visibility = View.GONE
        }

        if (!isFingerprintEnabled) {
            pin_fingerprint_login.visibility = View.GONE
        }

        pin_verification_code.setMaxLength(pinLength)
        if (!isPinHidden) {
            pin_verification_code.inputType = InputType.TYPE_CLASS_NUMBER
        }
    }

    fun onClick(view: View) {

        if(view.id == R.id.pin_one ) {
            currentPinInput += "1"
            pin_verification_code.setText(currentPinInput)

        }
        else if(view.id == R.id.pin_two){
            currentPinInput += "2"
            pin_verification_code.setText(currentPinInput)
        }
        else if(view.id == R.id.pin_three){
            currentPinInput += "3"
            pin_verification_code.setText(currentPinInput)

        }
        else if(view.id == R.id.pin_four){
            currentPinInput += "4"
            pin_verification_code.setText(currentPinInput)

        }
        else if(view.id == R.id.pin_five){
            currentPinInput += "5"
            pin_verification_code.setText(currentPinInput)

        }
        else if(view.id == R.id.pin_six){
            currentPinInput += "6"
            pin_verification_code.setText(currentPinInput)

        }
        else if(view.id == R.id.pin_seven){
            currentPinInput += "7"
            pin_verification_code.setText(currentPinInput)

        }
        else if(view.id == R.id.pin_eight){
            currentPinInput += "8"
            pin_verification_code.setText(currentPinInput)

        }
        else if(view.id == R.id.pin_nine) {
            currentPinInput += "9"
            pin_verification_code.setText(currentPinInput)

        }
        else if(view.id == R.id.pin_zero){
            currentPinInput += "0"
            pin_verification_code.setText(currentPinInput)

        }
        else if (view.id == R.id.pin_cancel_btn){

            currentPinInput = ""
            pin_verification_code.setText(initpin)

        }
        else if(view.id == R.id.pin_email_login){
            val homeIntent = Intent(this,EmailPasswordActivity::class.java)
            startActivityForResult(homeIntent, SignInUI.REQUEST_CODE)
            finish()
        }
        }

    private fun validateFields() : Boolean {
        val formValidator = FormValidator(this@PinActivity)

        val pinValidator = formValidator.validatePin(pin_verification_code.text.toString(), pinLength)
        if (!pinValidator.isFormValid()) {
            pin_verification_code.error = pinValidator.responseMessage
            Toast.makeText(this@PinActivity, pin_verification_code.error, Toast.LENGTH_SHORT).show()

        }

        return formValidator.isFormValid
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val pinIntent = Intent()
        pinIntent.putExtra(SignInUI.PARAM_SIGN_IN_TYPE, SignInUI.PIN_FORM)
        setResult(SignInUI.RESULT_CANCEL, pinIntent)
        finish()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        setResult(resultCode, data)
        finish()
    }

}
