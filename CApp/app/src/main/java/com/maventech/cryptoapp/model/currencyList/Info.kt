package com.maventech.cryptoapp.model.currencyList

import com.google.gson.annotations.SerializedName

data class Info (

	@SerializedName("timestamp") val timestamp : Int,
	@SerializedName("rate") val rate : Double
)