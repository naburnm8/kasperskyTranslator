package ru.bmstu.naburnm8.translator.data.network

import kotlinx.serialization.Serializable

@Serializable
data class Translation (
    val text: String,
    val note: String?
)

@Serializable
data class Meaning (
    val id: Long,
    val translation: Translation,
    val previewUrl: String,
    val imageUrl: String,
)

@Serializable
data class Word (
    val id: Long,
    val text: String,
    val meanings: List<Meaning>
)