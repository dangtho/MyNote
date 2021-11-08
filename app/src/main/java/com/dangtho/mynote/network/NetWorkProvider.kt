package com.dangtho.mynote.network

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class NetWorkProvider {
    companion object {
                private const val BASE_URL = "https://api.weatherapi.com/"
//        private const val BASE_URL = "https://api.tiki.vn/"
        private val okHttpClient =
            OkHttpClient.Builder().addInterceptor(CustomInterceptor()).build()
        private var instance: ApiService? = null
        fun getInstance(): ApiService? {
            if (instance == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
                    .build()
                instance = retrofit.create(ApiService::class.java)
            }
            return instance
        }
    }
}

class CustomInterceptor : Interceptor {
    companion object {
        const val TAG = "CustomerInterCeptor.class"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.e(TAG, chain.request().url().toString())
        return chain.proceed(chain.request())
    }
}