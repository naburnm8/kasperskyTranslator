package ru.bmstu.naburnm8.translator.viewModel.mainActivity

enum class Route {
    Translate, Favourites
}

data class MainActivityState(
    val route: Route = Route.Translate,
)