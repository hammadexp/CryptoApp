package com.maventech.cryptoapp.model.price

data class PriceRequest (
    val deadline:Int,
    val productid:Int,
    val standard:Int,
    val nopage:Int
        )