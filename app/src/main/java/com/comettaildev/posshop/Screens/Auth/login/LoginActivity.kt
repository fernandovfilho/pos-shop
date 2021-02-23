package com.comettaildev.posshop.screens.auth.login


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.comettaildev.posshop.R
import com.comettaildev.posshop.screens.auth.createAccount.CreateAccountActivity

class LoginActivity : AppCompatActivity() {

    lateinit var backButton: ImageView
    lateinit var createNewAccount: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        title = resources.getString(R.string.action_sign_in_short)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        init()
    }

    private fun init() {
        createNewAccount = findViewById(R.id.createNewAccount)
        createNewAccount.setOnClickListener {
            var intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener { finish() }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        finish()
        return super.onOptionsItemSelected(item)
    }

}
