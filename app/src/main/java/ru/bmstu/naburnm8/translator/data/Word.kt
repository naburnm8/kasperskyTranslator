package ru.bmstu.naburnm8.translator.data

data class Translation (
    val text: String,
    val note: String?
)

data class Meaning (
    val id: Long,
    val translation: Translation,
    val previewUrl: String,
    val imageUrl: String,
)

data class Word (
    val id: Long,
    val text: String,
    val meanings: List<Meaning>
)