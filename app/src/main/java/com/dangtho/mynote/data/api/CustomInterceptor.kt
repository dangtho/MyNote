package com.dangtho.mynote.data.api

import android.util.Log
import com.dangtho.mynote.data.Repository.MainApiRepository
import com.dangtho.mynote.data.Repository.MainDataBaseRepository
import com.dangtho.mynote.data.database.UrlEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject

class CustomInterceptor @Inject constructor(private val mainDataBaseRepository: MainDataBaseRepository) :
    Interceptor {
    companion object {
        const val TAG = "CustomerInterCeptor.class"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        Log.e(TAG, chain.request().url().toString())

        CoroutineScope(Dispatchers.IO).launch {
            val cal = Calendar.getInstance()
            val request = chain.request()
            val response = chain.proceed(request)
            var urlEntity = UrlEntity()
            urlEntity.urlLink = request.url().toString()
            urlEntity.details = response.body()?.string().toString()
            urlEntity.dateTime = cal.timeInMillis
            mainDataBaseRepository.insert(urlEntity)
        }
        return chain.proceed(chain.request())
    }
}