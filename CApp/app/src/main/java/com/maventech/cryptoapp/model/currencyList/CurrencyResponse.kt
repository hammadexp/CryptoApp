package com.maventech.cryptoapp.model.currencyList

data class CurrencyResponse(
    val success:Boolean,
    val terms:String,
    val target:String,
    val rates:Rates

)

data class Rates(
    val hashMap: HashMap<String,Int>
)

