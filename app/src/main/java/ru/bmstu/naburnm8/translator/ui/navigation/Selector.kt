package ru.bmstu.naburnm8.translator.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import org.koin.androidx.compose.koinViewModel
import ru.bmstu.naburnm8.translator.R
import ru.bmstu.naburnm8.translator.ui.mainActivity.MainActivityViewModel
import ru.bmstu.naburnm8.translator.ui.mainActivity.Route
import ru.bmstu.naburnm8.translator.ui.theme.TranslatorTheme

@Composable
fun Selector(
    modifier: Modifier = Modifier,
    viewModel: MainActivityViewModel = koinViewModel(),
) {
    Row(modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background).padding(top = 10.dp, bottom = 30.dp,),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        var currentButton by rememberSaveable {mutableStateOf(Route.Translate)}
        BaseButton(
            painter = painterResource(id = R.drawable.translate),
            contentDescription = stringResource(id = R.string.translate),
            isSelected = currentButton == Route.Translate,
        ) {
            viewModel.changeScreen(Route.Translate)
            currentButton = Route.Translate
        }
        BaseButton(
            painter = painterResource(id = R.drawable.history),
            contentDescription = stringResource(id = R.string.history),
            isSelected = currentButton == Route.History,
        ) {
            viewModel.changeScreen(Route.History)
            currentButton = Route.History
        }
        BaseButton(
            painter = painterResource(id = R.drawable.favourites),
            contentDescription = stringResource(R.string.favourites),
            isSelected = currentButton == Route.Favourites
        ) {
            viewModel.changeScreen(Route.Favourites)
            currentButton = Route.Favourites
        }
    }
}

@Preview
@Composable
fun SelectorPreviewDark () {
    TranslatorTheme(darkTheme = true) {
        Selector(viewModel = MainActivityViewModel())
    }
}

@Preview
@Composable
fun SelectorPreviewLight () {
    TranslatorTheme (darkTheme = false) {
        Selector(viewModel = MainActivityViewModel())
    }
}