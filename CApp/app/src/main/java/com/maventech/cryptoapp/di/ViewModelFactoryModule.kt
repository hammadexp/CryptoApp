package com.maventech.cryptoapp.di

import androidx.lifecycle.ViewModelProvider
import com.maventech.cryptoapp.viewmodel.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(modelProvider: ViewModelProviderFactory):
            ViewModelProvider.Factory

}