package com.maventech.cryptoapp.repository

class FakeCurrencyRepository :CryptoRepository{
    override suspend fun getCurrencyRates(apiKey: String): Any {
    return "null"
    }

    override suspend fun getCurrencies(apiKey: String): Any {
        TODO("Not yet implemented")
    }

    override suspend fun convert(apiKey: String, from: String, to: String, amount: String): Any {
        TODO("Not yet implemented")
    }
}