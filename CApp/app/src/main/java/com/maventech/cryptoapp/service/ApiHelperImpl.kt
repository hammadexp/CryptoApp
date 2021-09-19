package com.mindorks.example.coroutines.data.api

import com.matecho.wms.service.ApiInterface

class ApiHelperImpl(private val apiService: ApiInterface) : ApiHelper {

    override suspend fun getProducts() = apiService.getProductList("",1,1)

}