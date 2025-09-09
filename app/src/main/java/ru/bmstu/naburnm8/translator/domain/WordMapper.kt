package ru.bmstu.naburnm8.translator.domain

import ru.bmstu.naburnm8.translator.data.network.Word
import ru.bmstu.naburnm8.translator.data.database.WordDatabaseEntity

class WordMapper {
    companion object {
        fun map(word: Word): WordDomain {
            return WordDomain(
                word = word.text,
                translation = word.meanings[0].translation.text,
            )
        }
        fun map(wordDatabaseEntity: WordDatabaseEntity): WordDomain {
            return WordDomain(
                word = wordDatabaseEntity.word,
                translation = wordDatabaseEntity.translation,
                dbId = wordDatabaseEntity.id,
                isInFavourites = wordDatabaseEntity.isInFavourites
            )
        }
        fun toDto(wordDomain: WordDomain): WordDatabaseEntity {
            return if (wordDomain.dbId == -1L) WordDatabaseEntity(
                word = wordDomain.word,
                translation = wordDomain.translation,
                isInFavourites = wordDomain.isInFavourites
            ) else
                WordDatabaseEntity(
                    word = wordDomain.word,
                    translation = wordDomain.translation,
                    isInFavourites = wordDomain.isInFavourites,
                    id = wordDomain.dbId
                )
        }
    }
}