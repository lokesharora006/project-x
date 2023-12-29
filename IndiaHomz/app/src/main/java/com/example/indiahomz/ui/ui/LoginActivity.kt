package com.example.indiahomz.ui.ui

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.indiahomz.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class LoginActivity : AppCompatActivity() {

    private lateinit var registerTv: TextView
    private lateinit var emailView: EditText
    private lateinit var passwordView: EditText
    private lateinit var registerButton: Button
    private lateinit var forgotPasswordTv: TextView
    private lateinit var fStore: FirebaseFirestore

    private val firebaseAuthHelper = FirebaseAuthHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        init()

        registerButton.setOnClickListener(View.OnClickListener {
            if(isEverythingValid()
            ){
                firebaseAuthHelper.login(emailView.text.toString(),passwordView.text.toString())
                    .addOnSuccessListener(this, OnSuccessListener<AuthResult> {
                        Toast.makeText(this,"Login Successfully",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    })
                    .addOnFailureListener(this, OnFailureListener {
                        Toast.makeText(this,"Email or Password is incorrect",Toast.LENGTH_SHORT).show()
                    })
            }

        })

        forgotPasswordTv.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,ForgotPasswordActivity::class.java))
        })

        registerTv.setOnClickListener(View.OnClickListener {
            val registerIntent = Intent(this, RegisterActivity::class.java)
            startActivity(registerIntent)
            finish()
        })

    }

    fun init(){
        registerTv = findViewById(R.id.registerText)
        forgotPasswordTv = findViewById(R.id.forgotPasswordText)
        emailView = findViewById(R.id.emailEditTextLogin)
        passwordView = findViewById(R.id.passwordEditTextLogin)
        registerButton = findViewById(R.id.buttonLogin)
        fStore = FirebaseFirestore.getInstance()
    }

    private fun isEverythingValid(): Boolean {
        val Email = emailView.text.toString()
        val pass = passwordView.text.toString()

        if (Email == "" || pass == "") {
            Toast.makeText(
                this@LoginActivity,
                "Please Fill All Information",
                Toast.LENGTH_SHORT
            ).show()

            return false;
        } else {
            return Patterns.EMAIL_ADDRESS.matcher(Email).matches()


        }
    }

    fun onForgotPasswordClicked(email: String) {
        firebaseAuthHelper.sendPasswordResetEmail(email) { success ->
            if (success) {
                // Show a success message, inform the user to check their email
            } else {
                // Show an error message, inform the user about the failure
            }
        }
    }
}