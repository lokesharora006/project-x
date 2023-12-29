package com.example.indiahomz.ui.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.indiahomz.R

class ForgotPasswordActivity : AppCompatActivity() {
    private lateinit var sendOtpButton: Button
    private lateinit var emailText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        init()

        emailText.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
                sendOtpButton.isEnabled = true
            }
        })
        sendOtpButton.setOnClickListener {
            FirebaseAuthHelper().isEmailRegistered(emailText.text.toString()) { isRegistered ->
                if (isRegistered) {
                    // Email is already registered
                    startActivity(Intent(this, OtpActivity::class.java))
                } else {
                    // Email is not registered
                    // Proceed with registration or other actions
                    Toast.makeText(this, "Email not exits", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun init(){
        sendOtpButton = findViewById(R.id.buttonSendOtp)
        emailText = findViewById(R.id.editTextEmail2)
    }
}