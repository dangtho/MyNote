package com.dangtho.mynote.di.application

import android.content.Context
import androidx.room.Room
import com.dangtho.mynote.data.api.ApiHelper
import com.dangtho.mynote.data.api.ApiHelperImpl
import com.dangtho.mynote.data.api.ApiService
import com.dangtho.mynote.data.api.CustomInterceptor
import com.dangtho.mynote.data.database.AppDataBase
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class MyNoteApplicationModule {

    @Provides
    fun provideBaseUrl() = "https://api.weatherapi.com/"

    @Provides
    @Singleton
    fun provideInterceptor(interceptor: CustomInterceptor): Interceptor = interceptor

    @Provides
    @Singleton
    fun provideOkhttp(interceptor: Interceptor) = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String) = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @Provides
    @Singleton
    fun provideDataBase(@ApplicationContext context: Context): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, "MyNote.db")
            .allowMainThreadQueries().build()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DataBaseName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseURL