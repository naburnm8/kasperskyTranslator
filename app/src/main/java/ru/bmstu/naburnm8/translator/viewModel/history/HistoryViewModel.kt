package ru.bmstu.naburnm8.translator.viewModel.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.bmstu.naburnm8.translator.data.database.WordDatabaseRepository
import ru.bmstu.naburnm8.translator.domain.WordDomain

class HistoryViewModel(private val repo: WordDatabaseRepository) : ViewModel() {
    val stateFlow: StateFlow<List<WordDomain>> = repo.observe().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun toggleFavourite(wordDomain: WordDomain) {
        viewModelScope.launch {
            if (wordDomain.isInFavourites) {
                repo.unsetFavourite(wordDomain.dbId)
            } else {
                repo.setFavourite(wordDomain.dbId)
            }
        }
    }


}