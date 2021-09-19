package com.maventech.cryptoapp.repository

import com.matecho.wms.repository.BaseRepository
import com.matecho.wms.service.ApiInterface
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    val apiInterface: ApiInterface
) : BaseRepository() {

    /**
     * Api Calling
     */

    suspend fun getCurrencyRates(apiKey: String): Any {
        val data =
            safeApiCall({ apiInterface.getCurrencyList(apiKey).execute() }, "No response")
        return data!!
    }

    suspend fun convert(apiKey: String,from:String,to:String,amount:String): Any {
        val data =
            safeApiCall({ apiInterface.convert(apiKey,from,to,amount).execute() }, "No response")
        return data!!
    }

//9d3c003b79b9751ef0326e219f0ebd2c


}