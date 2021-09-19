package com.matecho.wms.model

import android.text.TextUtils

data class UserLogin(var emailAddress: String, var password: String) {

    /* &&Patterns.EMAIL_ADDRESS.matcher(getUsername()).matches()*/
    val isEmailValid: Boolean
        get() = !TextUtils.isEmpty(emailAddress) /* &&Patterns.EMAIL_ADDRESS.matcher(getUsername()).matches()*/

    val isPasswordLengthGreaterThan5: Boolean
        get() = !TextUtils.isEmpty(password) && password.length > 5

}