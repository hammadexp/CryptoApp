package com.matecho.wms.service

import com.maventech.cryptoapp.model.product.Product
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {

    @GET("/products")
    fun getProducts(): Call<List<Product>>


}