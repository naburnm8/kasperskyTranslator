package ru.bmstu.naburnm8.translator.ui.mainActivity

enum class Route {
    Translate, History, Favourites
}

data class MainActivityState(
    val route: Route = Route.Translate,
)