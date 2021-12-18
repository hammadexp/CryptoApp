package com.maventech.cryptoapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jai.blueprint.data.network.NetworkResult
import com.matecho.wms.viewmodel.BaseViewModel
import com.maventech.cryptoapp.model.product.Product
import com.maventech.cryptoapp.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    val dashboardRepositoryDefault: ProductRepository,
    application: Application
) : BaseViewModel(dashboardRepositoryDefault) {
    var isLoading = MutableLiveData<Int>()
    var list=MutableLiveData<List<Product>>()
    var application=application


    suspend fun getProductList(): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            val data = dashboardRepositoryDefault.getProducts()
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    list.postValue(((data as NetworkResult.Success<List<Product>>).data ))
                    msg = Pair(0, "")
                }

                // error in api calling
                is NetworkResult.Error -> {
                    msg = Pair(
                        1,
                        ((data as NetworkResult.Error).error as IOException).message.toString()
                    )
                }
            }
            msg

            msg = Pair(0, "")
            msg
        }
    }





}