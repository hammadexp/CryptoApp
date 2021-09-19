package com.matecho.wms.service

import com.matecho.wms.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//import okhttp3.logging.HttpLoggingInterceptor;
/**
 * Created by sabaq on 3/18/2018.
 */
object ApiClient {
    @JvmField
    var BASE_URL = "https://dummyproducts-api.herokuapp.com"
    @JvmField
    var BASE_PATH = "https://dummyproducts-api.herokuapp.com"
    @JvmField
    var REFERER = ""
    var POST_URL = "/"
    var retrofit: Retrofit? = null

    //                    .authenticator(new TokenAuthenticator(AccessTokenRepository.getInstance()))
    @JvmStatic
    val client: Retrofit?
        get() {
            if (retrofit == null) {
                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY
                val builder = OkHttpClient.Builder()
                builder.addInterceptor { chain ->
                    val request = chain.request().newBuilder()
//                            .addHeader(AppConstants.AUTHORIZATION, AppConstants.TOKEN!!)
                            .build()
                    chain.proceed(request)
                }
                val okHttpClient = builder.addInterceptor(interceptor)
                        .readTimeout(60, TimeUnit.SECONDS)
                        .connectTimeout(60, TimeUnit.SECONDS) //                    .authenticator(new TokenAuthenticator(AccessTokenRepository.getInstance()))
                        .build()
                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(okHttpClient)
                        .build()
            }
            return retrofit
        }
}