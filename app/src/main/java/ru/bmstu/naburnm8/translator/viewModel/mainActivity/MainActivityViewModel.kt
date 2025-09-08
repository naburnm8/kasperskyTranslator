package ru.bmstu.naburnm8.translator.viewModel.mainActivity

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainActivityViewModel: ViewModel() {
    private val _stateFlow = MutableStateFlow(MainActivityState())
    val stateFlow = _stateFlow.asStateFlow()

    fun changeScreen(route: Route) {
        _stateFlow.value = MainActivityState(route = route)
    }

    fun reset() {
        _stateFlow.value = MainActivityState(route = Route.Translate)
    }
}