@file:Suppress("FunctionName")

package ru.bmstu.naburnm8.translator.ui.translate

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Color
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
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.ColorImage
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.AsyncImage
import coil3.compose.AsyncImagePreviewHandler
import coil3.compose.LocalAsyncImagePreviewHandler
import ru.bmstu.naburnm8.translator.R
import ru.bmstu.naburnm8.translator.domain.WordDomain
import ru.bmstu.naburnm8.translator.ui.theme.TranslatorTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider

@Composable
fun HistoryShortCard(
    modifier: Modifier = Modifier,
    onFavouriteToggle: (WordDomain) -> Unit,
    wordDomain: WordDomain,
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
            AsyncImage(
                model = wordDomain.previewUrl,
                contentDescription = null,
                modifier = Modifier.height(48.dp).width(48.dp).shadow(
                    elevation = 20.dp,
                    clip = true,
                    shape = CircleShape,
                    spotColor = MaterialTheme.colorScheme.primary,
                    ambientColor = MaterialTheme.colorScheme.primary
                ),
            )
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

@OptIn(ExperimentalCoilApi::class)
val previewHandler = AsyncImagePreviewHandler {
    ColorImage(Color.Red.toArgb())
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
fun HistoryShortCardPreviewLight() {
    TranslatorTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            HistoryShortCard(
                wordDomain = WordDomain(
                    word = "Computer",
                    translation = "Компьютер",
                    fullImageUrl = "",
                    previewUrl = "",
                    isInFavourites = true,
                ),
                onFavouriteToggle = {},
            )
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Preview
@Composable
fun HistoryShortCardPreviewDark() {
    TranslatorTheme(darkTheme = true) {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            HistoryShortCard(
                wordDomain = WordDomain(
                    word = "Computer",
                    translation = "Компьютер",
                    fullImageUrl = "",
                    previewUrl = "",
                ),
                onFavouriteToggle = {},
            )
        }
    }
}

@Composable
fun HistoryPeekCard(
    modifier: Modifier = Modifier,
    list: List<WordDomain>,
    onFavouriteToggle: (WordDomain) -> Unit
) {
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
        items(list) {
            word ->
                HistoryShortCard(
                    wordDomain = word,
                    onFavouriteToggle = onFavouriteToggle
                )
        }
    }
}


val mockList = listOf(
    WordDomain(
        word = "Computer",
        translation = "Компьютер",
        fullImageUrl = "",
        previewUrl = "",
    ),
    WordDomain(
        word = "Closet",
        translation = "Шкаф",
        fullImageUrl = "",
        previewUrl = "",
    ),
    WordDomain(
        word = "Kitchen",
        translation = "Кухня",
        fullImageUrl = "",
        previewUrl = "",
    ),
    WordDomain(
        word = "Fork",
        translation = "Вилка",
        fullImageUrl = "",
        previewUrl = "",
    ),
    )

@Preview
@Composable
fun HistoryPeekCardPreviewLight() {
    TranslatorTheme {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            HistoryPeekCard(
                list = mockList,
                onFavouriteToggle = {},
            )
        }
    }
}

@Preview
@Composable
fun HistoryPeekCardPreviewDark() {
    TranslatorTheme (darkTheme = true) {
        CompositionLocalProvider(LocalAsyncImagePreviewHandler provides previewHandler) {
            HistoryPeekCard(
                list = mockList,
                onFavouriteToggle = {},
            )
        }
    }
}