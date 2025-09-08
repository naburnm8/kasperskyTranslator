package ru.bmstu.naburnm8.translator.data.network

import android.accounts.NetworkErrorException
import android.net.http.NetworkException
import ru.bmstu.naburnm8.translator.data.Word
import ru.bmstu.naburnm8.translator.domain.WordDomain
import ru.bmstu.naburnm8.translator.domain.WordMapper

class SkyengRepository(private val skyengApi: SkyengApi) {
    suspend fun searchWord(word: String): Result<WordDomain> {
        val response = skyengApi.searchWord(word)
        if (response.isSuccessful) {
            val data = response.body()
            return if (data != null) {
                if (data.isNotEmpty()) {
                    Result.success(WordMapper.map(data[0]))
                } else {
                    Result.failure(Exception(ErrorType.NotFound.name))
                }
            } else {
                Result.failure(Exception(ErrorType.Empty.name))
            }
        }
        return Result.failure(NetworkErrorException(response.errorBody()!!.string()))
    }

    companion object {
        enum class ErrorType {
            Empty, NotFound
        }
    }
}