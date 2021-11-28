package com.dangtho.mynote.data.database

import androidx.lifecycle.LiveData
import com.dangtho.mynote.data.model.LoginResponse
import com.dangtho.mynote.data.model.PersonInfoResponse
import javax.inject.Inject

class UrlHelperImpl @Inject constructor(private val urlDao: UrlDao, private val userDao: UserDao) :
    UrlHelper {
    override fun getAll(): LiveData<List<UrlEntity>> = urlDao.getAll()

    override fun insert(urlEntity: UrlEntity) = urlDao.insert(urlEntity)

    override  fun insert(user: PersonInfoResponse) = userDao.insert(user)

    override  fun insert(users: List<PersonInfoResponse>) = userDao.insertAll(users)

    override fun getAllUser(): LiveData<List<PersonInfoResponse>> = userDao.getAllUser()
    override fun getToken(id: String): LiveData<PersonInfoResponse> = userDao.getToken(id)
}