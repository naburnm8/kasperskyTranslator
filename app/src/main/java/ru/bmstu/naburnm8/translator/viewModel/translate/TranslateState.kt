package ru.bmstu.naburnm8.translator.viewModel.translate

import ru.bmstu.naburnm8.translator.domain.WordDomain

sealed class TranslateState {
    object Loading : TranslateState()
    object Empty : TranslateState()
    object Error : TranslateState()
    object NoTranslation : TranslateState()
    data class Success(val word: WordDomain) : TranslateState()
}