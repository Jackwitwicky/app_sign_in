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
        if(view.id == R.id.pin_one) {
            currentPinInput += "1"
            pin_verification_code.setText(currentPinInput)
        }
    }
}
