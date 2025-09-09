package ru.bmstu.naburnm8.translator.viewModel.translate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.bmstu.naburnm8.translator.data.database.WordDatabaseRepository
import ru.bmstu.naburnm8.translator.data.network.SkyengRepository
import ru.bmstu.naburnm8.translator.domain.WordDomain
import ru.bmstu.naburnm8.translator.domain.WordMapper

class TranslateViewModel(private val skyengRepository: SkyengRepository, private val dbRepo: WordDatabaseRepository): ViewModel() {
    private val _stateFlow = MutableStateFlow<TranslateState>(TranslateState.Empty)
    val stateFlow = _stateFlow.asStateFlow()


    fun search(query: String) {
        viewModelScope.launch {
            runCatching {
                _stateFlow.value = TranslateState.Loading
                val response = skyengRepository.searchWord(query)
                if (response.isFailure) {
                    if (response.exceptionOrNull()?.message == SkyengRepository.Companion.ErrorType.NotFound.name) {
                        _stateFlow.value = TranslateState.NoTranslation
                    } else {
                        _stateFlow.value = TranslateState.Error
                    }
                } else {
                    val domain = response.getOrNull()
                    if (domain == null) {
                        _stateFlow.value = TranslateState.Error
                    } else {
                        _stateFlow.value = TranslateState.Success(domain)
                        dbRepo.insert(WordMapper.toDto(domain))
                    }
                }
            }.onFailure {
                _stateFlow.value = TranslateState.Error
            }
        }
    }
}