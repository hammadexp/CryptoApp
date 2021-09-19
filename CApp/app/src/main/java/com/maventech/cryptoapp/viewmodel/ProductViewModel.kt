package com.maventech.cryptoapp.viewmodel

import com.maventech.cryptoapp.model.messages.Message
import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jai.blueprint.data.network.NetworkResult
import com.matecho.wms.model.BaseResponseApi
import com.matecho.wms.service.ApiClient
import com.matecho.wms.viewmodel.BaseViewModel
import com.maventech.cryptoapp.model.messages.SendMessageRequest
import com.maventech.cryptoapp.model.order.OrderItem
import com.maventech.cryptoapp.model.products.ProductListResponse
import com.maventech.cryptoapp.repository.ProductDataSource
import com.maventech.cryptoapp.repository.CryptoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class ProductViewModel @Inject constructor(
    val dashboardRepository: CryptoRepository,
    application: Application,
    private val api: ApiClient
) : BaseViewModel(dashboardRepository) {
    var isLoading = MutableLiveData<Int>()
    var productList=MutableLiveData<BaseResponseApi<ProductListResponse>>()
    var orderDetail=MutableLiveData<BaseResponseApi<OrderItem>>()
    var messageList=MutableLiveData<BaseResponseApi<List<SendMessageRequest>>>()
    var postMessageResponse=MutableLiveData<BaseResponseApi<Message>>()
    var postFileResponse=MutableLiveData<BaseResponseApi<String>>()

    var application=application


    suspend fun getOrderDetail(orderId: Int): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            val data = dashboardRepository.getOrderByOrderId(orderId)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    orderDetail.postValue(((data as NetworkResult.Success<BaseResponseApi<OrderItem>>).data ))
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


    val products= Pager(PagingConfig(pageSize = 10)){
        ProductDataSource(api,"")
    }.flow.cachedIn(viewModelScope)
}