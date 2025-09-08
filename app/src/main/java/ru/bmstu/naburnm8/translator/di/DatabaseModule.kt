package ru.bmstu.naburnm8.translator.di

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module
import ru.bmstu.naburnm8.translator.data.database.TranslatorDatabase

val dbModule = module {
    single {
        Room.databaseBuilder(get<Context>(), TranslatorDatabase::class.java, "translator.db").fallbackToDestructiveMigration(true).build()
    }
    single {get<TranslatorDatabase>().wordDao() }
}