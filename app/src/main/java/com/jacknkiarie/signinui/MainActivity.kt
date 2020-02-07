package com.jacknkiarie.signinui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        email_password_pin_login.setOnClickListener {
            val pinLoginIntent = Intent(this@MainActivity, PinActivity::class.java)
            startActivity(pinLoginIntent)
        }
    }
}
