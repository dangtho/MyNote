package com.dangtho.mynote.data.Repository

import androidx.room.Room
import androidx.room.migration.Migration
import androidx.room.migration.bundle.SchemaBundle
import androidx.room.testing.MigrationTestHelper
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import androidx.test.platform.app.InstrumentationRegistry
import com.dangtho.mynote.data.database.AppDataBase
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.io.File
import java.nio.channels.AsynchronousFileChannel.open
import java.nio.channels.AsynchronousServerSocketChannel.open
import java.nio.channels.FileChannel.open

class MainDataBaseRepositoryTesat {
    @get:Rule
    val testHelper = MigrationTestHelper(
        InstrumentationRegistry.getInstrumentation(),
        AppDataBase::class.java.canonicalName,
        FrameworkSQLiteOpenHelperFactory()
    )
private val context = InstrumentationRegistry.getInstrumentation().context
    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun testMigratedFrom1_2() {
        var runMigration = testHelper.createDatabase(DATABASE_NAME_TEST, 3).apply {
            close()
        }

        runMigration = testHelper.runMigrationsAndValidate(
            DATABASE_NAME_TEST,
            3,
            true,
            AppDataBase.migration_3_4
        )
        val actualVersion = runMigration.version
        val dab = getMigratedDatabase().userDao()
        assertEquals(3, actualVersion)
    }

    private fun getMigratedDatabase() = Room.databaseBuilder(
        InstrumentationRegistry.getInstrumentation().targetContext,
        AppDataBase::class.java, DATABASE_NAME_TEST
    ).addMigrations(AppDataBase.migration_1_2, AppDataBase.migration_2_3, AppDataBase.migration_3_4)
        .build().apply {
            openHelper.writableDatabase
            close()
        }

    companion object {
        private const val DATABASE_NAME_TEST = "dataTest.db"
        private val migration_2_3 = object : Migration(2, 3) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE users RENAME column loginToken to loginToken")
            }
        }
    }
}