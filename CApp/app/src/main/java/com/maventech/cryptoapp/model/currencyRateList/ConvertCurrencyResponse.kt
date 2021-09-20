package com.maventech.cryptoapp.model.currencyRateList

import com.google.gson.annotations.SerializedName

data class ConvertCurrencyResponse (

	@SerializedName("success") val success : Boolean,
	@SerializedName("terms") val terms : String,
	@SerializedName("privacy") val privacy : String,
	@SerializedName("query") val query : Query,
	@SerializedName("info") val info : Info,
	@SerializedName("result") val result : Double
)