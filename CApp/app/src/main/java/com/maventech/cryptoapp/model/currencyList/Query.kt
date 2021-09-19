package com.maventech.cryptoapp.model.currencyList

import com.google.gson.annotations.SerializedName

data class Query (

	@SerializedName("from") val from : String,
	@SerializedName("to") val to : String,
	@SerializedName("amount") val amount : Int
)