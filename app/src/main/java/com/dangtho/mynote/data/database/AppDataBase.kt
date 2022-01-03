package com.dangtho.mynote.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dangtho.mynote.data.model.LoginResponse
import com.dangtho.mynote.data.model.PersonEntity
import com.dangtho.mynote.data.model.UserEntity
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import javax.inject.Singleton

@Database(
    entities = [UrlEntity::class, UserEntity::class, PersonEntity::class],
    version = 4,
    exportSchema = true
)
@Singleton
@TypeConverters(MyTypeConverters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun urlLinkDao(): UrlDao
    abstract fun userDao(): UserDao
    abstract fun personDao(): PersonDao

    companion object {
        suspend fun getStringSql(context: Context): String = withContext(Dispatchers.IO) {
            var resultSql = ""
            val ass = context.resources.assets
            val im = ass.open("PesonInfo.sql")
            val buff = BufferedReader(InputStreamReader(im))
            var str: String
            while (buff.readLine().also { str = it } != null) {
                resultSql += str
            }
            resultSql
        }

        val migration_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE users ADD COLUMN pub_year INTEGER NOT NULL DEFAULT 0")
            }
        }

        val migration_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE users RENAME COLUMN token to loginToken")
            }
        }
        val migration_3_4 = object : Migration(3, 4) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE users ADD COLUMN migr4 INTEGER NOT NULL DEFAULT 0")
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