package com.dangtho.mynote.data.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UrlEntity(
    @PrimaryKey(autoGenerate = true)
    var urlId: Int = 0,

    @ColumnInfo(name = "url_link")
    var urlLink: String = "",

    @ColumnInfo(name = "date_time")
    var dateTime: Long = 0L,

    @ColumnInfo(name = "detail_response")
    var details: String = ""
)
