package ru.bmstu.naburnm8.translator.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface WordDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(word: WordDatabaseEntity): Long

    @Query("SELECT * FROM words ORDER BY createdAt DESC")
    fun observe(): Flow<List<WordDatabaseEntity>>

    @Query("SELECT * FROM words WHERE isInFavourites = 1 ORDER BY createdAt DESC")
    fun observeFavourites(): Flow<List<WordDatabaseEntity>>

    @Query("UPDATE words SET isInFavourites = 1 WHERE id = :id")
    suspend fun setFavourite(id: Long)

    @Query("UPDATE words SET isInFavourites = 0 WHERE id = :id")
    suspend fun unsetFavourite(id: Long)

    @Query("DELETE FROM words WHERE id = :id")
    suspend fun deleteById(id: Long)

    @Query("DELETE FROM words")
    suspend fun clear()
}