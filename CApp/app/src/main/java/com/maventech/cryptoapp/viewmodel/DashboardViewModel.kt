package com.matecho.wms.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jai.blueprint.data.network.NetworkResult
import com.matecho.wms.model.BaseResponseApi
import com.matecho.wms.model.educationLevel.EducationLevel
import com.matecho.wms.repository.ProductRepository
import com.maventech.cryptoapp.model.dashboard.Dashboard
import com.maventech.cryptoapp.model.deadline.Deadline
import com.maventech.cryptoapp.model.order.OrderRequest
import com.maventech.cryptoapp.model.order.PostOrderResponse
import com.maventech.cryptoapp.model.pages.Page
import com.maventech.cryptoapp.model.price.PriceRequest
import com.maventech.cryptoapp.model.price.PriceResponse
import com.maventech.cryptoapp.model.projectTypes.ProjectType
import com.maventech.cryptoapp.model.standard.Standard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.IOException
import javax.inject.Inject

class DashboardViewModel @Inject constructor(val dashboardRepository: ProductRepository, application: Application) : BaseViewModel(dashboardRepository) {
    var errorLogin = MutableLiveData<String>()
    var isLoading = MutableLiveData<Int>()
    var isFiltered = MutableLiveData<Boolean>()
    var educationLevelList=MutableLiveData<BaseResponseApi<List<EducationLevel>>>()
    var dashboardData=MutableLiveData<BaseResponseApi<Dashboard>>()
    var projectTypeList=MutableLiveData<BaseResponseApi<List<ProjectType>>>()
    var deadlineList=MutableLiveData<BaseResponseApi<List<Deadline>>>()
    var discountAmount=MutableLiveData<BaseResponseApi<String>>()
    var pageList=MutableLiveData<BaseResponseApi<List<Page>>>()
    var standardList=MutableLiveData<BaseResponseApi<List<Standard>>>()
    var price=MutableLiveData<BaseResponseApi<PriceResponse>>()
    var postOrderResponse=MutableLiveData<BaseResponseApi<PostOrderResponse>>()
    var postFileResponse=MutableLiveData<BaseResponseApi<PostOrderResponse>>()

    var application=application

    suspend fun getDashboardInfo(customerId: Int): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            val data = dashboardRepository.getDashboardInfo(customerId)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    dashboardData.postValue(((data as NetworkResult.Success<BaseResponseApi<Dashboard>>).data ))
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

    suspend fun fetchEducationLevels(): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
           /* if (dashboardRepository.clientsDao.getSize() == 0)*/
                val data = dashboardRepository.fetchEducationLevels()
                when ((data as NetworkResult<Any>)) {
                    is NetworkResult.Success<Any> -> {
                        educationLevelList.postValue(((data as NetworkResult.Success<BaseResponseApi<List<EducationLevel>>>).data ))
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

    suspend fun fetchProjectTypes( levelId:Int): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            /* if (dashboardRepository.clientsDao.getSize() == 0)*/
            val data = dashboardRepository.fetchProjectTypes(levelId)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    projectTypeList.postValue(((data as NetworkResult.Success<BaseResponseApi<List<ProjectType>>>).data ))
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

    suspend fun fetchDeadline(productId:Int): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            /* if (dashboardRepository.clientsDao.getSize() == 0)*/
            val data = dashboardRepository.fetchDeadlines(productId)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    deadlineList.postValue(((data as NetworkResult.Success<BaseResponseApi<List<Deadline>>>).data ))
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

          /*  msg = Pair(0, "")
            msg*/
        }
    }

    suspend fun fetchDiscount(discountCode:String): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            val data = dashboardRepository.fetchDiscount(discountCode)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    discountAmount.postValue(((data as NetworkResult.Success<BaseResponseApi<String>>).data ))
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

         /*   msg = Pair(0, "")
            msg*/
        }
    }

    suspend fun fetchPages(productId:Int): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            /* if (dashboardRepository.clientsDao.getSize() == 0)*/
            val data = dashboardRepository.fetchPages(productId)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    pageList.postValue(((data as NetworkResult.Success<BaseResponseApi<List<Page>>>).data ))
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

    suspend fun fetchStandards(productId:Int): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            /* if (dashboardRepository.clientsDao.getSize() == 0)*/
            val data = dashboardRepository.fetchStandard(productId)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    standardList.postValue(((data as NetworkResult.Success<BaseResponseApi<List<Standard>>>).data ))
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

    suspend fun getPrice(price: PriceRequest): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            /* if (dashboardRepository.clientsDao.getSize() == 0)*/
            val data = dashboardRepository.getPrice(price)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    this@DashboardViewModel.price.postValue(((data as NetworkResult.Success<BaseResponseApi<PriceResponse>>).data ))
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

    suspend fun saveOrder(orderRequest: OrderRequest): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            /* if (dashboardRepository.clientsDao.getSize() == 0)*/
            val data = dashboardRepository.saveOrder(orderRequest)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    postOrderResponse.postValue(((data as NetworkResult.Success<BaseResponseApi<PostOrderResponse>>).data ))
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

    suspend fun uploadAssignmentFile(orderId: Int, customerId:Int,file: File): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            /* if (dashboardRepository.clientsDao.getSize() == 0)*/
            val data = dashboardRepository.uploadAssignmentFile(application =application ,orderId,customerId,file)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    postFileResponse.postValue(((data as NetworkResult.Success<BaseResponseApi<PostOrderResponse>>).data ))
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

    suspend fun fetchEducationLevelFromDatabase(): LiveData<List<EducationLevel>> {
        return withContext(Dispatchers.IO) {
            dashboardRepository.clientsDao.fetchAllData()
        }
    }
}