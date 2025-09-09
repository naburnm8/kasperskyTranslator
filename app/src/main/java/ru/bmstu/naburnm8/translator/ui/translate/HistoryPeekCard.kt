@file:Suppress("FunctionName")

package ru.bmstu.naburnm8.translator.ui.translate

import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
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

@Composable
fun HistoryShortCard(
    modifier: Modifier = Modifier,
    wordDomain: WordDomain,
) {
    Row (
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
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
                )
            )
        }
    }
}