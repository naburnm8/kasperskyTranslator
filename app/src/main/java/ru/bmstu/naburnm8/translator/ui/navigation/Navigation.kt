@file:Suppress("FunctionName")

package ru.bmstu.naburnm8.translator.ui.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import ru.bmstu.naburnm8.translator.viewModel.mainActivity.MainActivityViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import ru.bmstu.naburnm8.translator.ui.favourites.FavouritesScreen
import ru.bmstu.naburnm8.translator.ui.history.HistoryScreen
import ru.bmstu.naburnm8.translator.viewModel.mainActivity.Route
import ru.bmstu.naburnm8.translator.ui.translate.TranslateScreen

@Composable
fun Navigation (viewModel: MainActivityViewModel = koinViewModel()) {

    val navController = rememberNavController()
    val state by viewModel.stateFlow.collectAsState()

    Scaffold(
        bottomBar = {
            Selector()
        }
    ) {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Route.Translate.name,
            modifier = Modifier.padding(innerPadding)
    ) {
        composable(Route.Translate.name) {
            TranslateScreen()
        }
        composable(Route.History.name) {
            HistoryScreen()
        }
        composable(Route.Favourites.name) {
            FavouritesScreen()
        }
    }
    }

    LaunchedEffect(state.route) {
        val target = state.route.name
        val current = navController.currentDestination?.route

        if (current != target) {
            navController.navigate(target) {
                launchSingleTop = true
                restoreState = true
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
            }
        }
    }

}