package com.maventech.cryptoapp.model.currencyList

import com.maventech.cryptoapp.model.currencyRateList.CurrencyItem

data class CurrencyListResponse (
    val success:Boolean,
    val crypto:Map<String,Currency>
        )

data class Currency(
    val name:String,
    val symbol:String,
    val icon:String
)