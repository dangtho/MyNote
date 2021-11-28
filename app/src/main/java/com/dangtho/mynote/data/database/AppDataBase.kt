package com.dangtho.mynote.data.database

import androidx.room.*
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dangtho.mynote.data.model.LoginResponse
import com.dangtho.mynote.data.model.PersonInfoResponse
import com.google.gson.Gson
import javax.inject.Singleton

@Database(
    entities = [UrlEntity::class, PersonInfoResponse::class],
    version = 3,
    exportSchema = false
)
@Singleton
@TypeConverters(MyTypeConverters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun urlLinkDao(): UrlDao
    abstract fun userDao(): UserDao

    companion object {
        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE users ADD COLUMN pub_year INTEGER NOT NULL DEFAULT 0")
            }
        }

        val migration_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE users ADD COLUMN token LoginResponse DEFAULT null")
            }
        }
    }
}

class MyTypeConverters {
    @TypeConverter
    fun loginResponseToString(loginResponse: LoginResponse?): String? =
        Gson().toJson(loginResponse)

    @TypeConverter
    fun stringToLoginResponse(value: String?): LoginResponse? =
        Gson().fromJson(value, LoginResponse::class.java)
}