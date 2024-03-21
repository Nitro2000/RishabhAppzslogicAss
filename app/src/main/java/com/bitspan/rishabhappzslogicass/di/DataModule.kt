package com.bitspan.rishabhappzslogicass.di

import android.content.Context
import com.bitspan.rishabhappzslogicass.storage.DataStoreManager
import com.bitspan.rishabhappzslogicass.storage.DataStoreManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn (SingletonComponent::class)
@Module
class DataModule {


    @Provides
    @Singleton
    fun provideDataStoreManager(@ApplicationContext appContext : Context): DataStoreManager {
        return DataStoreManagerImpl(appContext)
    }
}