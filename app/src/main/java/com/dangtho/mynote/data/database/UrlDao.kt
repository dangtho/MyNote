package com.dangtho.mynote.data.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.dangtho.mynote.data.model.LoginResponse
import com.dangtho.mynote.data.model.PersonInfoResponse

@Dao
interface BaseDao<T> {
    @Insert(onConflict = REPLACE)
    fun insertAll(item: List<T>)

    @Insert(onConflict = REPLACE)
    fun insert(item: T)

    @Delete
    fun delete(item: T)

}

@Dao
interface UrlDao : BaseDao<UrlEntity> {
    @Query("SELECT * FROM UrlEntity ORDER BY date_time DESC")
    fun getAll(): LiveData<List<UrlEntity>>
}

@Dao
interface UserDao : BaseDao<PersonInfoResponse> {
    @Query("SELECT * FROM users ORDER BY first_name DESC")
    fun getAllUser(): LiveData<List<PersonInfoResponse>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getToken(id: String): LiveData<PersonInfoResponse>
}