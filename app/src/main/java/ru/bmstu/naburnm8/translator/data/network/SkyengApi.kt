package ru.bmstu.naburnm8.translator.data.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.bmstu.naburnm8.translator.data.Word

interface SkyengApi {
    @GET("/api/public/v1/words/search")
    suspend fun searchWord(@Query("search") word: String, @Query("page") page: Int = 1, @Query("pageSize") pageSize: Int = 1): Response<List<Word>>
}