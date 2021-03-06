package com.maventech.cryptoapp.model

class APIError {
    private val statusCode = 0
    private val message: String? = null
    fun status(): Int {
        return statusCode
    }

    fun message(): String? {
        return message
    }
}