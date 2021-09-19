package com.maventech.cryptoapp.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.matecho.wms.service.ApiClient
import com.matecho.wms.service.ApiInterface
import com.maventech.cryptoapp.model.products.Data

class ProductDataSource(
    private val api:ApiClient,
    private val apiKey:String
) : PagingSource<Int, Data>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPageNumber = params.key ?: 0
            val mApiInterface: ApiInterface = ApiClient.client?.create(ApiInterface::class.java)!!
            val response = mApiInterface.getProductList(apiKey,5,nextPageNumber).execute()
            LoadResult.Page(
                data = response.body()?.result?.data!!,
                prevKey = if (nextPageNumber < 1) null else nextPageNumber - 1,
                nextKey = if (response.body()?.result?.data?.isNotEmpty() == true) nextPageNumber + 1 else null
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Data>): Int? {
        return null
    }
}