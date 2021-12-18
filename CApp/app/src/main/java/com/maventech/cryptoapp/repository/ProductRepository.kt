package com.maventech.cryptoapp.repository

import com.matecho.wms.repository.BaseRepository
import com.matecho.wms.service.ApiInterface
import javax.inject.Inject

interface ProductRepository {

    /**
     * Api Calling
     */

    suspend fun getProducts(): Any

}