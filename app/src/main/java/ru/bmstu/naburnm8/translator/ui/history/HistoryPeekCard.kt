@file:Suppress("FunctionName")

package ru.bmstu.naburnm8.translator.ui.history

import androidx.compose.foundation.background

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bmstu.naburnm8.translator.R
import ru.bmstu.naburnm8.translator.domain.WordDomain
import ru.bmstu.naburnm8.translator.ui.theme.TranslatorTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.ui.res.stringResource

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import ru.bmstu.naburnm8.translator.ui.translate.StyledBox

@Composable
fun HistoryShortCard(
    modifier: Modifier = Modifier,
    onFavouriteToggle: (WordDomain) -> Unit,
    wordDomain: WordDomain,
    onDeleteClick: (WordDomain) -> Unit,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        HorizontalDivider(thickness = 2.dp)
        Row(
            modifier = Modifier.fillMaxWidth().padding(top = 8.dp).padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            IconButton(
                onClick = { onDeleteClick(wordDomain) },
                modifier = Modifier.height(48.dp).width(48.dp).shadow(
                    elevation = 20.dp,
                    clip = true,
                    shape = CircleShape,
                    spotColor = MaterialTheme.colorScheme.primary,
                    ambientColor = MaterialTheme.colorScheme.primary
                ).background(MaterialTheme.colorScheme.onBackground),
            ) {
                Icon(
                    painter = painterResource(R.drawable.delete),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.padding(end = 10.dp).height(32.dp).width(32.dp)
                )
                Column(
                    modifier = Modifier,
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        text = wordDomain.word,
                        color = MaterialTheme.colorScheme.primary,
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = wordDomain.translation,
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
                Icon(
                    painter = painterResource(R.drawable.arrow),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.padding(start = 10.dp).height(32.dp).width(32.dp).rotate(180f)
                )
            }
            IconButton(
                onClick = { onFavouriteToggle(wordDomain) },
                modifier = Modifier.height(48.dp).width(48.dp).shadow(
                    elevation = 20.dp,
                    clip = true,
                    shape = CircleShape,
                    spotColor = MaterialTheme.colorScheme.primary,
                    ambientColor = MaterialTheme.colorScheme.primary
                ).background(MaterialTheme.colorScheme.onBackground),
            ) {
                Icon(
                    painter = if (wordDomain.isInFavourites) painterResource(R.drawable.favourites_added) else painterResource(
                        R.drawable.favourites
                    ),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary,
                )
            }
        }
        HorizontalDivider(thickness = 2.dp)
    }
}


@Preview
@Composable
fun HistoryShortCardPreviewLight() {
    TranslatorTheme {

            HistoryShortCard(
                wordDomain = WordDomain(
                    word = "Computer",
                    translation = "Компьютер",
                    isInFavourites = true,
                ),
                onFavouriteToggle = {},
                onDeleteClick = {},
            )

    }
}

@Preview
@Composable
fun HistoryShortCardPreviewDark() {
    TranslatorTheme(darkTheme = true) {

            HistoryShortCard(
                wordDomain = WordDomain(
                    word = "Computer",
                    translation = "Компьютер",
                ),
                onFavouriteToggle = {},
                onDeleteClick = {},
            )

    }
}


@Composable
fun WordList(
    modifier: Modifier = Modifier,
    onFavouriteToggle: (WordDomain) -> Unit,
    onDeleteClick: (WordDomain) -> Unit,
    list: List<WordDomain>,
) {
    if (list.isNotEmpty()) {
        LazyColumn(
            modifier = modifier.shadow(
                elevation = 20.dp,
                clip = true,
                shape = RoundedCornerShape(20.dp),
                ambientColor = MaterialTheme.colorScheme.primary,
                spotColor = MaterialTheme.colorScheme.primary,
            ).background(MaterialTheme.colorScheme.onBackground),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(list) { word ->
                HistoryShortCard(
                    wordDomain = word,
                    onFavouriteToggle = onFavouriteToggle,
                    onDeleteClick = onDeleteClick,
                )
            }
        }
    } else {
        StyledBox {
            Text (
                text = stringResource(R.string.empty),
                color = MaterialTheme.colorScheme.primary,
                fontSize = 18.sp,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center,
            )

        }
    }
}

@Composable
fun HistoryPeekCard(
    modifier: Modifier = Modifier,
    list: List<WordDomain>,
    onFavouriteToggle: (WordDomain) -> Unit,
    onDeleteClick: (WordDomain) -> Unit,
) {
    Column (
        modifier = modifier,
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(R.drawable.history),
                contentDescription = null,
                modifier = Modifier.height(32.dp).width(32.dp),
                tint = MaterialTheme.colorScheme.primary,
            )
            Text (
                text = stringResource(R.string.history),
                fontSize = 24.sp,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        WordList(
            onFavouriteToggle = onFavouriteToggle,
            onDeleteClick = onDeleteClick,
            list = list,
        )
    }
}


val mockList = listOf(
    WordDomain(
        word = "Computer",
        translation = "Компьютер",
    ),
    WordDomain(
        word = "Closet",
        translation = "Шкаф",
    ),
    WordDomain(
        word = "Kitchen",
        translation = "Кухня",
    ),
    WordDomain(
        word = "Fork",
        translation = "Вилка",
    ),
    )

@Preview
@Composable
fun HistoryPeekCardPreviewLight() {
    TranslatorTheme {

            HistoryPeekCard(
                list = mockList,
                onFavouriteToggle = {},
                onDeleteClick = {},
            )

    }
}

@Preview
@Composable
fun HistoryPeekCardPreviewDark() {
    TranslatorTheme (darkTheme = true) {

            HistoryPeekCard(
                list = mockList,
                onFavouriteToggle = {},
                onDeleteClick = {},
            )

    }
}