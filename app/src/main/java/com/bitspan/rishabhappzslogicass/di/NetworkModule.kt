package com.bitspan.rishabhappzslogicass.di

import com.bitspan.rishabhappzslogicass.data.Constant
import com.bitspan.rishabhappzslogicass.data.service.MovieService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {

    @Provides
    fun provideBaseUrl() = Constant.BASE_URL


    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {

        val client = OkHttpClient.Builder()


        client.addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
        client.connectTimeout(3, TimeUnit.MINUTES)
            .retryOnConnectionFailure(true)
            .writeTimeout(3, TimeUnit.MINUTES) // write timeout
            .readTimeout(3, TimeUnit.MINUTES)
            .connectionPool(ConnectionPool(0, 5, TimeUnit.MINUTES))

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .client(client.build())
            .baseUrl(baseUrl)
            .build()
    }



    @Provides
    @Singleton
    fun provideHomeApiService(retrofit: Retrofit): MovieService = retrofit.create(MovieService::class.java)

}