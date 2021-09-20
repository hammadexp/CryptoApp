package com.maventech.cryptoapp.di;

import com.matecho.wms.view.fragments.CurrencyConvertFragment;
import com.maventech.cryptoapp.ui.fragment.CurrencyRateListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract CurrencyRateListFragment contributeOrderListFragment();

    @ContributesAndroidInjector
    abstract CurrencyConvertFragment contributeOrderDetailFragment();


}