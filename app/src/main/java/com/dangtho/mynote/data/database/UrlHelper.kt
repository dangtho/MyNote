package com.dangtho.mynote.data.database

import androidx.lifecycle.LiveData
import com.dangtho.mynote.data.model.PersonEntity
import com.dangtho.mynote.data.model.UserEntity
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.selects.SelectClause1

interface UrlHelper {
    fun getAll(): LiveData<List<UrlEntity>>

    fun insert(urlEntity: UrlEntity)

    fun insert(user: UserEntity)

    fun insert(user: List<UserEntity>)

    fun getAllUser(): List<UserEntity>

    fun getToken(id: String): LiveData<UserEntity>

    suspend fun getAllPerson(): LiveData<List<PersonEntity>>?
}