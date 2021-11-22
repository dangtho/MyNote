package com.dangtho.mynote.data.api

import android.util.Log
import com.dangtho.mynote.di.application.MyNoteApplication
import com.dangtho.mynote.data.database.AppDataBase
import com.dangtho.mynote.data.database.UrlEntity
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Inject

class CustomInterceptor @Inject constructor(private val dataBase: AppDataBase) : Interceptor  {
    companion object {
        const val TAG = "CustomerInterCeptor.class"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.e(TAG, chain.request().url().toString())
        val cal = Calendar.getInstance()
        val request = chain.request()
        val context = MyNoteApplication()
        val response = chain.proceed(request)

//        CoroutineScope(Dispatchers.Default).launch {
//            val urlEntity = UrlEntity()
//            urlEntity.urlLink = request.url().toString()
//            urlEntity.dateTime = cal.timeInMillis
//            urlEntity.details = response.body()?.string() ?: ""
//            dataBase.urlLinkDao().insert(urlEntity)
//        }
        return response
    }
}