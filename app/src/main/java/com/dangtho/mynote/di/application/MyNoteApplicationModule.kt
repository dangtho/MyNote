package com.dangtho.mynote.di.application

import android.content.Context
import androidx.room.Room
import com.dangtho.mynote.data.api.ApiHelper
import com.dangtho.mynote.data.api.ApiHelperImpl
import com.dangtho.mynote.data.api.ApiService
import com.dangtho.mynote.data.api.CustomInterceptor
import com.dangtho.mynote.data.database.AppDataBase
import com.dangtho.mynote.data.database.UrlHelper
import com.dangtho.mynote.data.database.UrlHelperImpl
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
    companion object {
        const val BASE_URL_WEATHER = "https://api.weatherapi.com/"
        const val BASE_URL_REQRES_In = "https://reqres.in/"
    }

    @BaseURL
    @Provides
    @Singleton
    fun provideBaseUrl() = BASE_URL_REQRES_In

    @Provides
    @Singleton
    fun provideInterceptor(interceptor: CustomInterceptor): Interceptor = interceptor

    @Provides
    @Singleton
    fun provideOkhttp(interceptor: Interceptor): OkHttpClient = OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .build()


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, @BaseURL BASE_URL: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideApiHelper(apiHelper: ApiHelperImpl): ApiHelper = apiHelper

    @DataBaseName
    @Provides
    @Singleton
    fun provideDataBaseName(): String = "myNote.db"

    @Provides
    @Singleton
    fun provideDataBase(
        @ApplicationContext context: Context,
        @DataBaseName dataBaseName: String
    ): AppDataBase =
        Room.databaseBuilder(context, AppDataBase::class.java, dataBaseName)
            .allowMainThreadQueries().build()

    @Provides
    @Singleton
    fun urlDao(appDataBase: AppDataBase) = appDataBase.urlLinkDao()

    @Provides
    @Singleton
    fun urlHelper(urlHelper: UrlHelperImpl): UrlHelper = urlHelper
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DataBaseName

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseURL
