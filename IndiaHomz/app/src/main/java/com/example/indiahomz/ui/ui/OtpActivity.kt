package com.example.indiahomz.ui.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.indiahomz.R


class OtpActivity : AppCompatActivity() {

    lateinit var otp_textbox_one: EditText
    lateinit var otp_textbox_two:EditText
    lateinit var otp_textbox_three:EditText
    lateinit var otp_textbox_four:EditText
    lateinit var continueButton: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otp)
        init()

        val edit = arrayOf(otp_textbox_one, otp_textbox_two, otp_textbox_three, otp_textbox_four)

        otp_textbox_one.addTextChangedListener(GenericTextWatcher(otp_textbox_one, edit, continueButton))
        otp_textbox_two.addTextChangedListener(GenericTextWatcher(otp_textbox_two, edit, continueButton))
        otp_textbox_three.addTextChangedListener(GenericTextWatcher(otp_textbox_three, edit, continueButton))
        otp_textbox_four.addTextChangedListener(GenericTextWatcher(otp_textbox_four, edit, continueButton))

        continueButton.setOnClickListener(View.OnClickListener {
            if(otp_textbox_one.text.toString() == "" || otp_textbox_two.text.toString() == "" || otp_textbox_three.text.toString() == "" || otp_textbox_four.text.toString() == "" ){

            }else{
//                startActivity(Intent(this,PasswordActivity::class.java))
            }

        })
    }

    private fun init(){
        continueButton = findViewById(R.id.buttonContinue)
        otp_textbox_one = findViewById(R.id.otp_edit_box1);
        otp_textbox_two = findViewById(R.id.otp_edit_box2);
        otp_textbox_three = findViewById(R.id.otp_edit_box3);
        otp_textbox_four = findViewById(R.id.otp_edit_box4);
    }
}