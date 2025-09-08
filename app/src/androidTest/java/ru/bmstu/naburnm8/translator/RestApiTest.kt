package ru.bmstu.naburnm8.translator

import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.get
import ru.bmstu.naburnm8.translator.data.network.RetrofitBuilder
import ru.bmstu.naburnm8.translator.data.network.SkyengApi
import ru.bmstu.naburnm8.translator.data.network.SkyengRepository

@RunWith(AndroidJUnit4::class)
class RestApiTest {
    @Test
    fun getWord() {
        val repo = SkyengRepository(RetrofitBuilder.build().create(SkyengApi::class.java))
        runBlocking {
            launch() {
               val response = repo.searchWord("Computer")
                if (response.isSuccess) {
                    println(response.getOrNull())
                } else {
                    throw Exception()
                }
            }
        }
    }
}