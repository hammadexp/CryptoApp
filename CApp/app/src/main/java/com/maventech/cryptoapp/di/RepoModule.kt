package com.jai.blueprint.di.module

import com.matecho.wms.service.ApiInterface
import com.maventech.cryptoapp.repository.CryptoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepoModule {
    @Provides
    @Singleton
    internal fun provideDashboardRepository(

        networkCall: ApiInterface
    ): CryptoRepository {
        return CryptoRepository( networkCall)
    }



}