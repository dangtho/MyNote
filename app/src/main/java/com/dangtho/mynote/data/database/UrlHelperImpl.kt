package com.dangtho.mynote.data.database

import androidx.lifecycle.LiveData
import javax.inject.Inject

class UrlHelperImpl @Inject constructor(private val urlLinkDao: UrlLinkDao) : UrlHelper {
    override fun getAll(): LiveData<List<UrlEntity>> = urlLinkDao.getAll()

    override fun insert(users: UrlEntity) = urlLinkDao.insert(users)

}