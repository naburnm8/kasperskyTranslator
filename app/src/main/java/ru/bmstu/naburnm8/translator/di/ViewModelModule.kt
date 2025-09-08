package ru.bmstu.naburnm8.translator.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.bmstu.naburnm8.translator.ui.mainActivity.MainActivityViewModel

val viewModelModule = module {
    viewModel {
        MainActivityViewModel()
    }
}