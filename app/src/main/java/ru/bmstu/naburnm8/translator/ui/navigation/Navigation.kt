package ru.bmstu.naburnm8.translator.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import ru.bmstu.naburnm8.translator.ui.mainActivity.MainActivityViewModel
import androidx.compose.runtime.getValue

@Composable
fun Navigation (viewModel: MainActivityViewModel = koinViewModel()) {
    val state by viewModel.stateFlow.collectAsState()
    val navController = rememberNavController()


}