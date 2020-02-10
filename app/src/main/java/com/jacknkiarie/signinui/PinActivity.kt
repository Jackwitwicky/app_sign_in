package com.jacknkiarie.signinui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jacknkiarie.signinui.models.FormValidator
import kotlinx.android.synthetic.main.activity_email_password.*
import kotlinx.android.synthetic.main.activity_pin.*

class PinActivity : AppCompatActivity() {

    var currentPinInput = ""
    var initpin = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pin)
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

        if (view.id == R.id.pin_check_btn){

            Toast.makeText(this@PinActivity, "Welcome", Toast.LENGTH_SHORT).show()

            val homeIntent = Intent(this,EmailPasswordActivity::class.java)
                startActivity(homeIntent)

        }

        pin_check_btn.setOnClickListener{
            validateFields()
        }


        }

    private fun validateFields() : Boolean {
        val formValidator = FormValidator(this@PinActivity)

        val pinValidator = formValidator.validatePin(pin_verification_code.text.toString())
        if (!pinValidator.isFormValid()) {
            pin_verification_code.error = pinValidator.responseMessage
        }

        return formValidator.isFormValid
    }


}
