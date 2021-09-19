package com.maventech.cryptoapp.di

import android.app.Application
import androidx.room.Room
import com.matecho.wms.db.ClientsDao
import com.matecho.wms.db.LeopardDatabase
import com.matecho.wms.utils.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule {
    //
    @Provides
    @Singleton
    internal fun provideDb(context: Application): LeopardDatabase {
        return Room.databaseBuilder(context, LeopardDatabase::class.java, AppConstants.DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }


    @Provides
    @Singleton
    internal fun providePlayerDao(context: Application): ClientsDao {
        return provideDb(context).clientsDao()
    }

}