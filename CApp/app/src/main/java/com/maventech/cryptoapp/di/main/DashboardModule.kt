package com.maventech.dagger2practice.di.auth

import com.matecho.wms.db.ClientsDao
import com.matecho.wms.service.ApiInterface
import com.maventech.cryptoapp.repository.CryptoRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DashboardModule {


    @Provides
    fun provideApi(retrofit: Retrofit): ApiInterface {
        return retrofit.create(ApiInterface::class.java)
    }


    @Provides
    fun provideDataManager(apiCall: ApiInterface): CryptoRepository {
        return CryptoRepository(apiCall)
    }

}