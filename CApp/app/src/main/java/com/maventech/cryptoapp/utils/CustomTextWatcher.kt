package com.matecho.wms.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import java.util.*

abstract class CustomTextWatcher //Notice I'm passing a view at the constructor, but you can pass other variables or whatever you need
(//Notice abstract class so we leave abstract method textWasChanged() for implementing class to define it
        private val myTextView //Remember EditText is a TextView so this works for EditText also
        : TextView) : TextWatcher {
    private var timer = Timer()
    private val DELAY = 500 //milliseconds of delay for timer
    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    override fun afterTextChanged(s: Editable) {
        timer.cancel()
        timer = Timer()
        timer.schedule(
                object : TimerTask() {
                    override fun run() {
                        textWasChanged()
                    }
                },
                DELAY
                        .toLong())
    }

    abstract fun textWasChanged() //Notice abstract method to leave implementation to implementing class

}