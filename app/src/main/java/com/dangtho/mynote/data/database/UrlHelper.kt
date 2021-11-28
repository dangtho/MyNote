package com.dangtho.mynote.data.database

import androidx.lifecycle.LiveData
import com.dangtho.mynote.data.model.LoginResponse
import com.dangtho.mynote.data.model.PersonInfoResponse

interface UrlHelper {
    fun getAll(): LiveData<List<UrlEntity>>

    fun insert(urlEntity: UrlEntity)

    fun insert(user: PersonInfoResponse)

    fun insert(user: List<PersonInfoResponse>)

    fun getAllUser(): LiveData<List<PersonInfoResponse>>

    fun getToken(id: String): LiveData<PersonInfoResponse>
}