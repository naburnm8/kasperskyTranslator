package ru.bmstu.naburnm8.translator.di

import org.koin.dsl.module
import retrofit2.Retrofit
import ru.bmstu.naburnm8.translator.data.network.RetrofitBuilder
import ru.bmstu.naburnm8.translator.data.network.SkyengApi

val networkModule = module {
    single {
        RetrofitBuilder.build()
    }

    single {
        get<Retrofit>().create(SkyengApi::class.java)
    }
}