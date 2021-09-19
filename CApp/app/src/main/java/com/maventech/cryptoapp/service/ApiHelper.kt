package com.mindorks.example.coroutines.data.api

import com.matecho.wms.model.BaseResponseApi
import com.maventech.cryptoapp.model.products.ProductListResponse
import retrofit2.Call


interface ApiHelper {

    suspend fun getProducts(): Call<BaseResponseApi<ProductListResponse>>

}