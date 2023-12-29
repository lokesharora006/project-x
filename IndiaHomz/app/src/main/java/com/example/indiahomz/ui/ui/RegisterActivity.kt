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
import com.google.android.gms.tasks.Tasks
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
class RegisterActivity : AppCompatActivity() {

    private lateinit var loginTv: TextView
    private lateinit var usernameView: EditText
    private lateinit var emailView:EditText
    private lateinit var passwordView:EditText
    private lateinit var registerButton: Button
    private lateinit var fStore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        init()

        registerButton.setOnClickListener(View.OnClickListener {
            if(isEverythingValid()
            ){
                FirebaseAuthHelper().register(emailView.text.toString(),passwordView.text.toString())
                    .addOnSuccessListener(this, OnSuccessListener<AuthResult> {
//                        if(AuthResult.NULL)
                        var user: FirebaseUser? = FirebaseAuthHelper().getCurrentUser()
                        var df: DocumentReference? = user?.let { it1 ->
                            fStore.collection("Users").document(
                                it1.getUid())
                        }
                        var userInfo = HashMap<String,String>()
                        userInfo["username"] = usernameView.text.toString()
                        userInfo["userEmail"] = emailView.text.toString()
                        userInfo["isAdmin"]="1"

                        df?.set(userInfo)

                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    })
                    .addOnFailureListener(this, OnFailureListener {

                    })
            }

        })

        loginTv.setOnClickListener(View.OnClickListener {
            val registerIntent = Intent(this, LoginActivity::class.java)
            startActivity(registerIntent)
            finish()
        })
    }

    fun init(){
        usernameView = findViewById(R.id.usernameEditTextRegister)
        emailView = findViewById(R.id.emailEditTextRegister)
        passwordView = findViewById(R.id.passwordEditTextRegister)
        registerButton = findViewById<Button>(R.id.buttonRegister)
        loginTv = findViewById<TextView>(R.id.loginText)
        fStore = FirebaseFirestore.getInstance()
    }


    private fun isEverythingValid(): Boolean {
        val userName = usernameView.text.toString()
        val Email = emailView.text.toString()
        val pass = passwordView.text.toString()

        if (userName == "" || Email == "" || pass == "") {
            Toast.makeText(
                this@RegisterActivity,
                "Please Fill All Information",
                Toast.LENGTH_SHORT
            ).show()

            return false;
        } else {
            if (Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {
//                    if (!dao.emailValidate(Email)) {

                if (!checkUser(Email)) {
                    // Go to the sign-up screen
                    if (pass.length >= 8) {
//                            dao.insert(Model(userName, Email, pass))
//                            startActivity(Intent(this@RegisterActivity, AvatarActivity::class.java))
//                            finish()
                        return true;

                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Password Length Must Be Greater Than 8",
                            Toast.LENGTH_SHORT
                        ).show()
                        return false;

                    }
                }else{
                    Toast.makeText(
                            this@RegisterActivity,
                            "Email Already Exists",
                            Toast.LENGTH_SHORT
                        ).show()
                }

            }else{

            }
            return true;
        }
    }
}


fun checkUser(email: String): Boolean {
    val collectionReference = FirebaseFirestore.getInstance().collection("users")

    return try {
        val task = collectionReference.whereEqualTo("email", email).get()
        val querySnapshot = Tasks.await(task)
        querySnapshot.documents.isEmpty()
    } catch (e: Exception) {
        // Handle the exception
        false
    }
}
