package com.dangtho.mynote.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "tblPersonInfo")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    var id: Int = 0,

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    var firstName: String? = "",

    @ColumnInfo(name = "last_name")
    @SerializedName("last_name")
    var lastName: String? = ""
)