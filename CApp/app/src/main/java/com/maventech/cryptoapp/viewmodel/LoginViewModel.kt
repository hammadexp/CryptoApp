package com.matecho.wms.viewmodel

import android.app.Application
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.jai.blueprint.data.network.NetworkResult
import com.matecho.wms.model.BaseResponse
import com.matecho.wms.model.BaseResponseApi
import com.matecho.wms.model.UserLogin
import com.matecho.wms.model.UserRegister
import com.matecho.wms.model.login.LoginData
import com.matecho.wms.repository.LoginRepository
import com.matecho.wms.utils.InternetDetector
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    val repository: LoginRepository,
    val application: Application
) : BaseViewModel(repository) {
    var name = MutableLiveData<String>()
    var timeZone = MutableLiveData<String>()
    var username = MutableLiveData<String>()
    var password = MutableLiveData<String>()
    var confirmPassword = MutableLiveData<String>()
    var login = MutableLiveData<UserLogin>()
    var register = MutableLiveData<UserRegister>()
    var loginData = MutableLiveData<BaseResponseApi<LoginData>>()
    var errorLogin = MutableLiveData<String?>()
    var isLoading = MutableLiveData<Int>()


    private suspend fun fetchLogin(userLogin: UserLogin): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            userLogin
            val data = repository.login(userLogin)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    loginData.postValue(((data as NetworkResult.Success<BaseResponseApi<LoginData>>).data))
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

    private suspend fun fetchRegister(userLogin: UserRegister): Pair<Int, String> {
        return withContext(Dispatchers.IO) {
            var msg: Pair<Int, String>
            userLogin
            val data = repository.register(userLogin)
            when ((data as NetworkResult<Any>)) {
                is NetworkResult.Success<Any> -> {
                    loginData.postValue(((data as NetworkResult.Success<BaseResponseApi<LoginData>>).data))
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

    suspend fun onLoginClicked() {
        if (InternetDetector.isNetworkAvailable(application)) {
            val user = UserLogin(username.value!!, password.value!!)
            if (!user.isEmailValid) {
                errorLogin.postValue("Enter a valid email address")
            } else if (!user.isPasswordLengthGreaterThan5) {
                errorLogin.postValue("Password length should be greater than 5")
            } else {
                errorLogin.postValue(null)
                fetchLogin(user)
            }
            login.postValue(user)
        } else {
            errorLogin.postValue("Please connect to Internet")
            isLoading.postValue(View.GONE)
        }
    }

    suspend fun onRegisterClicked() {
        if (InternetDetector.isNetworkAvailable(application)) {
            val user =
                UserRegister(name.value!!, username.value!!, password.value!!, "", confirmPassword.value!!,"92")

            if (user.name.isEmpty()) {
                errorLogin.postValue("Enter your name")
            }
            if (!user.isEmailValid) {
                errorLogin.postValue("Enter a valid email address")
            } else if (!user.isPasswordLengthGreaterThan5) {
                errorLogin.postValue("Password length should be greater than 5")
            }
            else if(user.password!=user.confrimPassword){
                errorLogin.postValue("Password doesn't match with confirm password.")
            }
            else {
                errorLogin.postValue(null)
                fetchRegister(user)
            }
            register.postValue(user)
        } else {
            errorLogin.postValue("Please connect to Internet")
            isLoading.postValue(View.GONE)
        }
    }


}