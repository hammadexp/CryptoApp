package com.maventech.dagger2practice.di.auth

import androidx.lifecycle.ViewModel
import com.maventech.cryptoapp.viewmodel.CurrencyViewModel
import com.maventech.cryptoapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DashboardViewModelsModule {



    @Binds
    @IntoMap
    @ViewModelKey(CurrencyViewModel::class)
    abstract fun bindCurrencyViewModel(viewModel: CurrencyViewModel): ViewModel



}