package com.example.indiahomz.ui.ui

import com.example.indiahomz.R
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import android.widget.EditText


class GenericTextWatcher(view: View, private val editText: Array<EditText>, private val otpButton: Button) :
    TextWatcher {
    private val view: View

    init {
        this.view = view
    }

    override fun afterTextChanged(editable: Editable) {
        val text = editable.toString()
        val isOtpComplete = editText.all { it.text.toString().isNotEmpty() }

        // Enable the button when the OTP is complete
        otpButton.isEnabled = isOtpComplete
        when (view.getId()) {
            R.id.otp_edit_box1 -> if (text.length == 1) editText[1].requestFocus()
            R.id.otp_edit_box2 -> if (text.length == 1) editText[2].requestFocus() else if (text.length == 0) editText[0].requestFocus()
            R.id.otp_edit_box3 -> if (text.length == 1) editText[3].requestFocus() else if (text.length == 0) editText[1].requestFocus()
            R.id.otp_edit_box4 -> if (text.length == 0) editText[2].requestFocus()
        }
    }

    override fun beforeTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
    override fun onTextChanged(arg0: CharSequence, arg1: Int, arg2: Int, arg3: Int) {}
}