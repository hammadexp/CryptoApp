package com.maventech.cryptoapp.repository

import com.matecho.wms.repository.BaseRepository
import com.matecho.wms.service.ApiInterface
import javax.inject.Inject

interface CryptoRepository {

    /**
     * Api Calling
     */

    suspend fun getCurrencyRates(apiKey: String): Any

    suspend fun getCurrencies(apiKey: String): Any

    suspend fun convert(apiKey: String,from:String,to:String,amount:String): Any



}