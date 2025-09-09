@file:Suppress("FunctionName")

package ru.bmstu.naburnm8.translator.ui.favourites


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.bmstu.naburnm8.translator.R
import ru.bmstu.naburnm8.translator.domain.WordDomain
import ru.bmstu.naburnm8.translator.ui.history.WordList
import ru.bmstu.naburnm8.translator.ui.theme.TranslatorTheme
import ru.bmstu.naburnm8.translator.viewModel.favourites.FavouritesViewModel

@Composable
fun FavouritesScreen(
    viewModel: FavouritesViewModel = koinViewModel(),
) {
    val dbItems by viewModel.stateFlow.collectAsStateWithLifecycle()

    FavouritesContent(
        list = dbItems,
        onFavouriteClick = {viewModel.toggleFavourite(it)},
        onDeleteClick = {viewModel.delete(it)},
        modifier = Modifier.padding(16.dp)
    )
}


@Composable
fun FavouritesContent(
    modifier: Modifier = Modifier,
    list: List<WordDomain>,
    onFavouriteClick: (WordDomain) -> Unit,
    onDeleteClick: (WordDomain) -> Unit,
){
    Column (
        modifier = modifier,
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.favourites),
                contentDescription = stringResource(R.string.favourites),
                modifier = Modifier.height(32.dp).width(32.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
            Text (
                text = stringResource(R.string.favourites),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        WordList(
            onFavouriteToggle = onFavouriteClick,
            onDeleteClick = onDeleteClick,
            list = list,
        )
    }
}

@Preview
@Composable
fun FavouritesScreenPreview() {
    TranslatorTheme {
        FavouritesContent(
            list = listOf(),
            onFavouriteClick = {},
            onDeleteClick = {},
        )
    }
}