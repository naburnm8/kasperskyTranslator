package ru.bmstu.naburnm8.translator.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.bmstu.naburnm8.translator.viewModel.history.HistoryViewModel
import ru.bmstu.naburnm8.translator.viewModel.mainActivity.MainActivityViewModel
import ru.bmstu.naburnm8.translator.viewModel.translate.TranslateViewModel

val viewModelModule = module {
    viewModel {
        MainActivityViewModel()
    }
    viewModel {
        HistoryViewModel(get())
    }
    viewModel {
        TranslateViewModel(get(), get())
    }
}