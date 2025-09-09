package ru.bmstu.naburnm8.translator.viewModel.history

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.core.logger.Logger


import ru.bmstu.naburnm8.translator.data.database.WordDatabaseRepository
import ru.bmstu.naburnm8.translator.domain.WordDomain
import ru.bmstu.naburnm8.translator.domain.WordMapper

class HistoryViewModel(private val repo: WordDatabaseRepository) : ViewModel() {
    val stateFlow: StateFlow<List<WordDomain>> = repo.observe().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun insert(wordDomain: WordDomain) {
        viewModelScope.launch {
            repo.insert(WordMapper.toDto(wordDomain))
            Log.d("history", "Inserted ${wordDomain.word}")
        }
    }

    fun delete(wordDomain: WordDomain) {
        viewModelScope.launch {
            repo.deleteWord(wordDomain.dbId)
            Log.d("history", "Deleted ${wordDomain.word}")
        }
    }

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