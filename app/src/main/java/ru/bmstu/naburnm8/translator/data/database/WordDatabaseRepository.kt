package ru.bmstu.naburnm8.translator.data.database

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.bmstu.naburnm8.translator.domain.WordDomain
import ru.bmstu.naburnm8.translator.domain.WordMapper

class WordDatabaseRepository(
    private val wordDao: WordDao,
) {
    fun observe(): Flow<List<WordDomain>> {
        return wordDao.observe().map {list -> list.map { WordMapper.map(it) }}
    }

    fun observeFavourites(): Flow<List<WordDomain>> {
        return wordDao.observeFavourites().map {list -> list.map { WordMapper.map(it) }}
    }

    suspend fun clear() {
        wordDao.clear()
    }

    suspend fun setFavourite(id: Long) {
        wordDao.setFavourite(id)
    }

    suspend fun unsetFavourite(id: Long) {
        wordDao.unsetFavourite(id)
    }

    suspend fun deleteWord(id: Long) {
        wordDao.deleteById(id)
    }

    suspend fun insert(wordDatabaseEntity: WordDatabaseEntity) {
        wordDao.insert(wordDatabaseEntity)
    }
}