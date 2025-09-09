package ru.bmstu.naburnm8.translator.viewModel.favourites

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.bmstu.naburnm8.translator.data.database.WordDatabaseRepository
import ru.bmstu.naburnm8.translator.domain.WordDomain

class FavouritesViewModel(private val repo: WordDatabaseRepository): ViewModel() {
    val stateFlow: StateFlow<List<WordDomain>> = repo.observeFavourites().stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

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