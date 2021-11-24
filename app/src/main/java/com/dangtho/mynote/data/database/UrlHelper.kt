package com.dangtho.mynote.data.database

import androidx.lifecycle.LiveData

interface UrlHelper {
    fun getAll(): LiveData<List<UrlEntity>>

    fun insert(users: UrlEntity)
}