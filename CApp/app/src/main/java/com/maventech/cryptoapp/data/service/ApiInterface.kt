package com.matecho.wms.service

import com.maventech.cryptoapp.model.currencyList.CurrencyListResponse
import com.maventech.cryptoapp.model.currencyRateList.ConvertCurrencyResponse
import com.maventech.cryptoapp.model.currencyRateList.CurrencyResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/live")
    fun getCurrencyRateList(@Query("access_key") apiKey:String
                       ): Call<CurrencyResponse>

    @GET("/list")
    fun getCurrencies(@Query("access_key") apiKey:String
    ): Call<CurrencyListResponse>

    @GET("/convert")
    fun convert(@Query("access_key") apiKey:String,
                @Query("from") from:String,
                @Query("to") to:String,
                @Query("amount") amount:String
    ): Call<ConvertCurrencyResponse>
}