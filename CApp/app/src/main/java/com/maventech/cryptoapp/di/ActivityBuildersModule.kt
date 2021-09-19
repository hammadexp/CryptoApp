package com.maventech.cryptoapp.di

import com.maventech.dagger2practice.di.auth.DashboardModule
import com.maventech.dagger2practice.di.auth.DashboardViewModelsModule
import com.maventech.cryptoapp.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {
    @ContributesAndroidInjector(
        modules = [MainFragmentBuildersModule::class,DashboardViewModelsModule::class, DashboardModule::class]
    )
    abstract fun contributeMainActivity(): MainActivity


}