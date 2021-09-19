package com.matecho.wms.model

import android.text.TextUtils

data class UserRegister(var name:String,var email: String, var password: String, var timeZone:String,var confrimPassword:String,var country:String) {

    /* &&Patterns.EMAIL_ADDRESS.matcher(getUsername()).matches()*/
    val isEmailValid: Boolean
        get() = !TextUtils.isEmpty(email) /* &&Patterns.EMAIL_ADDRESS.matcher(getUsername()).matches()*/

    val isPasswordLengthGreaterThan5: Boolean
        get() = !TextUtils.isEmpty(password) && password.length > 5

}