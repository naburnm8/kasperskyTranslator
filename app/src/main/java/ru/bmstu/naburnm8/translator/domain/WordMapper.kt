package ru.bmstu.naburnm8.translator.domain

import ru.bmstu.naburnm8.translator.data.Word

class WordMapper {
    companion object {
        fun map(word: Word): WordDomain {
            return WordDomain(
                word = word.text,
                translation = word.meanings[0].translation.text,
                previewUrl = word.meanings[0].previewUrl,
                fullImageUrl = word.meanings[0].imageUrl
            )
        }
    }
}