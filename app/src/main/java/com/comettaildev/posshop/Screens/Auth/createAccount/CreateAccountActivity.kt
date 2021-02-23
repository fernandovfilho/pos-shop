package com.comettaildev.posshop.screens.auth.createAccount

import android.os.Bundle
import android.text.InputType
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.comettaildev.posshop.R


class CreateAccountActivity : AppCompatActivity() {

    lateinit var backButton: ImageView
    lateinit var editTextPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_account)

        title = resources.getString(R.string.action_sign_up_short)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        init()
    }

    private fun init() {
        editTextPassword = findViewById(R.id.editTextPassword)
        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener { finish() }
        drawableOnCLick()
    }

    private fun drawableOnCLick() {
        editTextPassword.setOnTouchListener(object : View.OnTouchListener {

            override fun onTouch(v: View?, event: MotionEvent): Boolean {
                val DRAWABLE_RIGHT = 2
                if (event.action == MotionEvent.ACTION_UP) {
                    if (event.rawX >= editTextPassword.right - editTextPassword.compoundDrawables[DRAWABLE_RIGHT].bounds.width()
                    ) {
                        editTextPassword.inputType =
                            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                        return true
                    }
                } else {
                    editTextPassword.inputType =
                        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS
                }
                return false
            }
        })
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