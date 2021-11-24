package com.dangtho.mynote.data.Repository

import androidx.lifecycle.LiveData
import androidx.room.Insert
import com.dangtho.mynote.data.api.ApiHelper
import com.dangtho.mynote.data.database.UrlEntity
import com.dangtho.mynote.data.database.UrlHelper
import javax.inject.Inject

class MainDataBaseRepository @Inject constructor(
    private val urlHelper: UrlHelper
) {
    /**---------------------------data base------------------------------------*/
    fun getAll(): LiveData<List<UrlEntity>> = urlHelper.getAll()

    fun insert(users: UrlEntity) = urlHelper.insert(users)

}