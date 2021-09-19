package com.maventech.dagger2practice.di.auth

import androidx.lifecycle.ViewModel
import com.matecho.wms.viewmodel.DashboardViewModel
import com.matecho.wms.viewmodel.LoginViewModel
import com.maventech.cryptoapp.viewmodel.ProductViewModel
import com.maventech.cryptoapp.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class DashboardViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(DashboardViewModel::class)
    abstract fun bindDashboardViewModel(viewModel: DashboardViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProductViewModel::class)
    abstract fun bindOrderViewModel(viewModel: ProductViewModel): ViewModel


    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel
}