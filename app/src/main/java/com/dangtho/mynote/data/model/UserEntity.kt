package com.dangtho.mynote.data.model

import androidx.room.*
import com.dangtho.mynote.data.database.MyTypeConverters
import com.google.gson.annotations.SerializedName
import java.sql.Date

@Entity(tableName = "users")
class UserEntity {

    @PrimaryKey
    @SerializedName("id")
    var id: String =""

    @ColumnInfo(name = "email")
    @SerializedName("email")
    var email: String =""

    @ColumnInfo(name = "first_name")
    @SerializedName("first_name")
    var firstName: String =""

    @ColumnInfo(name = "last_name")
    @SerializedName( "last_name")
    var lastName: String =""

    @ColumnInfo(name = "avatar")
    @SerializedName( "avatar")
    var avatar: String =""

    @ColumnInfo(name = "pub_year", defaultValue = "0")
    var pub_year: Int = 0
    @ColumnInfo(name = "migr4", defaultValue = "0")
    var migr4: Int = 0

    @ColumnInfo(name = "loginToken", defaultValue = "null")
    var loginToken: LoginResponse? = null
}