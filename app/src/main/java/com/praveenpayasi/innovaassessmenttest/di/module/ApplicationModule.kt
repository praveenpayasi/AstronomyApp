package com.praveenpayasi.innovaassessmenttest.di.module

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.praveenpayasi.innovaassessmenttest.data.api.NetworkService
import com.praveenpayasi.innovaassessmenttest.data.local.LocalStorageService
import com.praveenpayasi.innovaassessmenttest.utils.DefaultDispatcherProvider
import com.praveenpayasi.innovaassessmenttest.utils.DispatcherProvider
import com.praveenpayasi.innovaassessmenttest.utils.NetworkHelper
import com.praveenpayasi.innovaassessmenttest.utils.NetworkHelperImpl
import com.praveenpayasi.innovaassessmenttest.utils.logger.AppLogger
import com.praveenpayasi.innovaassessmenttest.utils.logger.Logger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule {

    @BaseUrl
    @Provides
    fun provideBaseUrl(): String = "https://api.nasa.gov/"

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun provideNetworkService(
        @BaseUrl baseUrl: String,
        gsonConverterFactory: GsonConverterFactory
    ): NetworkService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(gsonConverterFactory)
            .build()
            .create(NetworkService::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkHelper(@ApplicationContext context: Context): NetworkHelper {
        return NetworkHelperImpl(context)
    }

    @Provides
    @Singleton
    fun provideDispatcher() : DispatcherProvider = DefaultDispatcherProvider()


    @Provides
    @Singleton
    fun provideLogger(): Logger = AppLogger()

    @SharedPrefName
    @Provides
    fun provideSharedPrefName(): String = "nasa-shared-pref"

    @Provides
    @Singleton
    fun provideSharedPreferences(
        @ApplicationContext context: Context,
        @SharedPrefName sharedPrefName: String
    ): SharedPreferences {
        return context.getSharedPreferences(sharedPrefName, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideLocalStorageService(sharedPreferences: SharedPreferences,gson: Gson): LocalStorageService {
        return LocalStorageService(sharedPreferences,gson)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

}