package com.dangtho.mynote.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UrlLinkDao {
    @Query("SELECT * FROM UrlEntity")
    fun getAll(): LiveData<List<UrlEntity>>

    @Insert
    fun insert(users: UrlEntity)
}