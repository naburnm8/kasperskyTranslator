package ru.bmstu.naburnm8.translator.domain

import ru.bmstu.naburnm8.translator.data.network.Word
import ru.bmstu.naburnm8.translator.data.database.WordDatabaseEntity

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
        fun map(wordDatabaseEntity: WordDatabaseEntity): WordDomain {
            return WordDomain(
                word = wordDatabaseEntity.word,
                translation = wordDatabaseEntity.translation,
                previewUrl = wordDatabaseEntity.previewUrl,
                fullImageUrl = wordDatabaseEntity.fullImageUrl,
                dbId = wordDatabaseEntity.id,
                isInFavourites = wordDatabaseEntity.isInFavourites
            )
        }
        fun toDto(wordDomain: WordDomain): WordDatabaseEntity {
            return WordDatabaseEntity(
                word = wordDomain.word,
                translation = wordDomain.translation,
                previewUrl = wordDomain.previewUrl,
                fullImageUrl = wordDomain.fullImageUrl,
                id = wordDomain.dbId,
                isInFavourites = wordDomain.isInFavourites
            )
        }
    }
}