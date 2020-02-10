package com.jacknkiarie.signinui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_pin.*

class PinActivity : AppCompatActivity() {

    var currentPinInput = ""

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

        if (view.id == R.id.pin_check_btn){

        }

    }











}
