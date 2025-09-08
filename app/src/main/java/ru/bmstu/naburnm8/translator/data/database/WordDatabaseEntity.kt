package ru.bmstu.naburnm8.translator.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "words",
)
data class WordDatabaseEntity (
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val word: String,
    val translation: String,
    val fullImageUrl: String,
    val previewUrl: String,
    val createdAt: Long = System.currentTimeMillis(),
    val isInFavourites: Boolean = false,
)