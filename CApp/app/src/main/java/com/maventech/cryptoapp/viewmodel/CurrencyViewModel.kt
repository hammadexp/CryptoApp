package com.maventech.cryptoapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.jai.blueprint.data.network.NetworkResult
import com.matecho.wms.viewmodel.BaseViewModel
import com.maventech.cryptoapp.R
import com.maventech.cryptoapp.model.currencyList.CurrencyListResponse
import com.maventech.cryptoapp.model.currencyRateList.ConvertCurrencyResponse
import com.maventech.cryptoapp.model.currencyRateList.CurrencyResponse
import com.maventech.cryptoapp.repository.DefaultCryptoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class CurrencyViewModel @Inject constructor(
    val dashboardRepositoryDefault: DefaultCryptoRepository,
    application: Application
) : BaseViewModel(dashboardRepositoryDefault) {
    var isLoading = MutableLiveData<Int>()
    var list=MutableLiveData<CurrencyResponse>()
    var currencyListResponse=MutableLiveData<CurrencyListResponse>()
    var convertResponse=MutableLiveData<ConvertCurrencyResponse>()

    var application=application


    suspend fun getCurrencyRate(): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            val data = dashboardRepositoryDefault.getCurrencyRates(application.getString(R.string.api_key))
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    list.postValue(((data as NetworkResult.Success<CurrencyResponse>).data ))
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

    suspend fun getCurrencies(): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            val data = dashboardRepositoryDefault.getCurrencies(application.getString(R.string.api_key))
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    currencyListResponse.postValue(((data as NetworkResult.Success<CurrencyListResponse>).data ))
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

    suspend fun convert(from:String,to:String,amount:String): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            val data = dashboardRepositoryDefault.convert(application.getString(R.string.api_key),
                from,to,amount)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    convertResponse.postValue(((data as NetworkResult.Success<ConvertCurrencyResponse>).data ))
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