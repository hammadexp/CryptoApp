package com.matecho.wms.service

import com.matecho.wms.model.BaseResponseApi
import com.maventech.cryptoapp.model.products.ProductListResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/api/v1/products")
    fun getProductList(@Query("apikey") apiKey:String,
                       @Query("limit") limit:Int,
                       @Query("page") page:Int): Call<BaseResponseApi<ProductListResponse>>

}