package ru.bmstu.naburnm8.translator.domain

data class WordDomain (
    val word: String,
    val translation: String,
    val fullImageUrl: String,
    val previewUrl: String,
    val dbId: Long = -1,
    val isInFavourites: Boolean = false
)