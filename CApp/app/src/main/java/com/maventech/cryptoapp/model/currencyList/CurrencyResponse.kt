package com.maventech.cryptoapp.model.currencyList

import com.google.gson.JsonObject

data class CurrencyResponse(
    val success:Boolean,
    val terms:String,
    val target:String,
    val rates:Map<String,Double>

)

data class Rates(
    var hashMap: HashMap<String,Double>?=null
)

data class CurrencyItem(
    val name:String,
    val rate:String
)

