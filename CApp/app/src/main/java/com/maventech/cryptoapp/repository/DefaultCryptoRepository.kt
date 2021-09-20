package com.maventech.cryptoapp.repository

import com.matecho.wms.repository.BaseRepository
import com.matecho.wms.service.ApiInterface
import javax.inject.Inject

class DefaultCryptoRepository @Inject constructor(
    val apiInterface: ApiInterface
) : BaseRepository(),CryptoRepository {

    /**
     * Api Calling
     */

    override suspend fun getCurrencyRates(apiKey: String): Any {
        val data =
            safeApiCall({ apiInterface.getCurrencyRateList(apiKey).execute() }, "No response")
        return data!!
    }

    override suspend fun getCurrencies(apiKey: String): Any {
        val data =
            safeApiCall({ apiInterface.getCurrencies(apiKey).execute() }, "No response")
        return data!!
    }

    override suspend fun convert(apiKey: String, from:String, to:String, amount:String): Any {
        val data =
            safeApiCall({ apiInterface.convert(apiKey,from,to,amount).execute() }, "No response")
        return data!!
    }

//9d3c003b79b9751ef0326e219f0ebd2c


}