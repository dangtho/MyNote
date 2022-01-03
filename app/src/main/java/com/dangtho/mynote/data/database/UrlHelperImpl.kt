package com.dangtho.mynote.data.database

import androidx.lifecycle.LiveData
import com.dangtho.mynote.data.model.PersonEntity
import com.dangtho.mynote.data.model.UserEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UrlHelperImpl @Inject constructor(
    private val urlDao: UrlDao,
    private val userDao: UserDao,
    private val personDao: PersonDao
) :
    UrlHelper {
    override fun getAll(): LiveData<List<UrlEntity>> = urlDao.getAll()

    override fun insert(urlEntity: UrlEntity) = urlDao.insert(urlEntity)

    override fun insert(user: UserEntity) = userDao.insert(user)

    override fun insert(user: List<UserEntity>) = userDao.insertAll(user)

    override fun getAllUser(): List<UserEntity> = userDao.getAllUser()
    override fun getToken(id: String): LiveData<UserEntity> = userDao.getToken(id)
    override suspend fun getAllPerson(): LiveData<List<PersonEntity>>? =
        withContext(Dispatchers.IO) {
            personDao.getAllUser()
        }
}