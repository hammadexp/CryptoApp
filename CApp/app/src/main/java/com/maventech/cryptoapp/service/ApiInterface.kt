package com.matecho.wms.service

import com.matecho.wms.model.BaseResponseApi
import com.maventech.cryptoapp.model.currencyList.CurrencyResponse
import com.maventech.cryptoapp.model.products.ProductListResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/live")
    fun getCurrencyList(@Query("access_key") apiKey:String
                       ): Call<CurrencyResponse>

    @GET("/convert")
    fun convert(@Query("access_key") apiKey:String,
                @Query("from") from:String,
                @Query("to") to:String,
                @Query("amount") amount:String
    ): Call<CurrencyResponse>
}