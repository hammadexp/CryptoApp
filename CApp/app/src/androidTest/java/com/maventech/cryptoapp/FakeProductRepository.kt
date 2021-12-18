package com.maventech.cryptoapp

import com.jai.blueprint.data.network.NetworkResult
import com.maventech.cryptoapp.model.currencyList.CurrencyListResponse
import com.maventech.cryptoapp.model.currencyRateList.CurrencyResponse
import com.maventech.cryptoapp.repository.ProductRepository

open class FakeProductRepository : ProductRepository {
    override suspend fun getCurrencyRates(apiKey: String): Any {
        val mutableLiveData = CurrencyResponse(true,"","", emptyMap())
        return NetworkResult.Success(mutableLiveData)

    }

    override suspend fun getCurrencies(apiKey: String): Any {
        val mutableLiveData = CurrencyListResponse(true, emptyMap())
        return NetworkResult.Success(mutableLiveData)

    }

    override suspend fun convert(apiKey: String, from: String, to: String, amount: String): Any {
        TODO("Not yet implemented")
    }
}