package com.dangtho.mynote.data.Repository

import androidx.lifecycle.LiveData
import com.dangtho.mynote.data.database.UrlEntity
import com.dangtho.mynote.data.database.UrlHelper
import com.dangtho.mynote.data.model.PersonEntity
import com.dangtho.mynote.data.model.UserEntity
import javax.inject.Inject

class MainDataBaseRepository @Inject constructor(
    private val urlHelper: UrlHelper
) {
    /**---------------------------data base------------------------------------*/
    fun getAll(): LiveData<List<UrlEntity>> = urlHelper.getAll()

    fun insert(users: UrlEntity) = urlHelper.insert(users)

    fun insert(user: UserEntity) = urlHelper.insert(user)

    fun insert(users: List<UserEntity>) = urlHelper.insert(users)

    fun getAllUser() = urlHelper.getAllUser()

    fun getToken(id: String) = urlHelper.getToken(id)

    suspend fun getAllPerson(): LiveData<List<PersonEntity>>? = urlHelper.getAllPerson()
}