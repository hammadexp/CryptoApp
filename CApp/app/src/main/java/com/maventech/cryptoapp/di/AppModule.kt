package com.maventech.cryptoapp.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.matecho.wms.utils.AppConstants
import com.matecho.wms.utils.SharedPreference
import com.maventech.cryptoapp.R
import com.maventech.cryptoapp.model.currencyList.Rates
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideRetrofitInstance(application: Application): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val okHttpBuilder = OkHttpClient.Builder()
           val gson:Gson=GsonBuilder().registerTypeAdapter(Rates::class.java,MyDeserializer())
               .create()
            okHttpBuilder.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                val token =
                    SharedPreference(application).getStringValue(application.getString(R.string.token))
             /*   if (token != null)
                    request.addHeader(AppConstants.AUTHORIZATION, "Bearer "+token)*/

                chain.proceed(request.build())
            }

            val builder = Retrofit.Builder()
            val okHttpClient = okHttpBuilder.addInterceptor(interceptor)
                .readTimeout(60, TimeUnit.SECONDS)
                .connectTimeout(
                    60,
                    TimeUnit.SECONDS
                ) //                    .authenticator(new TokenAuthenticator(AccessTokenRepository.getInstance()))
                .build()
            return builder.baseUrl(AppConstants.APP_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient).build()
        }

        @Singleton
        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions.placeholderOf(R.drawable.success_bow)
                .error(R.drawable.error_circle)
        }

        @Singleton
        @Provides
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions):
                RequestManager {
            return Glide.with(application).setDefaultRequestOptions(requestOptions)
        }

        @Singleton
        @Provides
        fun provideAppDrawable(application: Application):
                Drawable {
            return ContextCompat.getDrawable(application, R.drawable.error_center_x)!!;
        }

        @Singleton
        @Provides
        fun provideSharedPreference(application: Application): SharedPreference {
            return SharedPreference(application)
        }

    }
}