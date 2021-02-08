package com.comettaildev.posshop

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.comettaildev.posshop.screens.auth.login.LoginActivity

class MainActivity : AppCompatActivity() {

    lateinit var buttonSignIn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        buttonSignIn = findViewById(R.id.buttonSignIn)
        buttonSignIn.setOnClickListener {
            val signInIntent = Intent(this, LoginActivity::class.java)
            startActivity(signInIntent)
        }
    }
}