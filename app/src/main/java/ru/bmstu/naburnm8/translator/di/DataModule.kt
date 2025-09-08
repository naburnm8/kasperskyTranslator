package ru.bmstu.naburnm8.translator.di

import org.koin.dsl.module
import ru.bmstu.naburnm8.translator.data.network.SkyengRepository

val dataModule = module {
    single {
        SkyengRepository(skyengApi = get())
    }
}