package com.maventech.cryptoapp.di;

import com.maventech.cryptoapp.ui.fragment.ProductDetailFragment;
import com.maventech.cryptoapp.ui.fragment.ProductListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProductListFragment contributeOrderListFragment();

    @ContributesAndroidInjector
    abstract ProductDetailFragment contributeOrderDetailFragment();


}