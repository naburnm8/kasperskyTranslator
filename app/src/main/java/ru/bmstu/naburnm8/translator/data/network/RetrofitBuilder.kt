package ru.bmstu.naburnm8.translator.data.network

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.kotlinx.serialization.asConverterFactory

class RetrofitBuilder {
    companion object {
        fun build(api: String = "https://dictionary.skyeng.ru/"): Retrofit {
            return Retrofit.Builder().baseUrl(api).addConverterFactory(Json {ignoreUnknownKeys = true}.asConverterFactory("application/json; charset=UTF-8".toMediaType())).build()
        }
    }
}