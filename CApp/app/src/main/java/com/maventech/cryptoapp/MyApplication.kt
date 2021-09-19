package com.maventech.cryptoapp

import android.content.Context
import android.os.StrictMode
import android.os.StrictMode.VmPolicy
import com.maventech.cryptoapp.di.AppComponent
import com.maventech.cryptoapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

open class MyApplication : DaggerApplication() {


    lateinit var wikiComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        val builder = VmPolicy.Builder()
        StrictMode.setVmPolicy(builder.build())
        appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
            private set
    }


    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
       return DaggerAppComponent.builder().application(this).build()
    }

}