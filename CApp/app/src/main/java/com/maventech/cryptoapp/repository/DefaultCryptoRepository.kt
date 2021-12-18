package com.maventech.cryptoapp.repository

import com.matecho.wms.repository.BaseRepository
import com.matecho.wms.service.ApiInterface
import javax.inject.Inject

class DefaultCryptoRepository @Inject constructor(
    val apiInterface: ApiInterface
) : BaseRepository(),ProductRepository {

    /**
     * Api Calling
     */


    override suspend fun getProducts(): Any {
        val data =
            safeApiCall({ apiInterface.getProducts().execute() }, "No response")
        return data!!
    }



//9d3c003b79b9751ef0326e219f0ebd2c


}