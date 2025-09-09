package ru.bmstu.naburnm8.translator.data.database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [WordDatabaseEntity::class],
    version = 2,
    exportSchema = true
)
abstract class TranslatorDatabase: RoomDatabase() {
    abstract fun wordDao(): WordDao
}