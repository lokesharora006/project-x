package com.example.hacktechproject

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import java.util.*

class UserDetailActivity : AppCompatActivity() {

    private lateinit var firstNameView: EditText
    private lateinit var lastNameView: EditText
    private lateinit var emailView: EditText
    private lateinit var dobView: EditText

    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)

        init()

        dobView.setOnClickListener(View.OnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                DatePickerDialog.OnDateSetListener { view: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
                    // Handle the selected date
                    val selectedDate = "$dayOfMonth/${monthOfYear + 1}/$year"
                    dobView.setText(selectedDate)
                },
                year,
                month,
                day
            )

            datePickerDialog.show()
        })

        submitButton.setOnClickListener(View.OnClickListener {
            if(firstNameView.text.toString() == "" || lastNameView.text.toString() == "" || emailView.text.toString() == "" || dobView.text.toString() == "" ){
                Toast.makeText(this,"Please Fill All Information",Toast.LENGTH_SHORT).show()

            }else{
                if (Patterns.EMAIL_ADDRESS.matcher(emailView.text.toString()).matches()) {
//                    startActivity(Intent(this,HomeActivity::class.java))

                }
            }
        })

    }

    fun init(){
        firstNameView = findViewById(R.id.nameEditText)
        lastNameView = findViewById(R.id.lastNameEditText)
        emailView = findViewById(R.id.emailEditText)
        submitButton = findViewById(R.id.buttonSubmit)
        dobView = findViewById(R.id.dobEditText)
    }
}