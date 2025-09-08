@file:Suppress("FunctionName")

package ru.bmstu.naburnm8.translator.ui.translate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bmstu.naburnm8.translator.R
import ru.bmstu.naburnm8.translator.ui.theme.TranslatorTheme

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchButtonClick: (String) -> Unit,
) {
    var text by remember { mutableStateOf("") }
    Row(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                .shadow(
                    elevation = 20.dp,
                    clip = true,
                    shape = RoundedCornerShape(25.dp),
                    spotColor = MaterialTheme.colorScheme.primary,
                    ambientColor = MaterialTheme.colorScheme.primary
                )
                .background(MaterialTheme.colorScheme.onBackground),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            TextField(
                value = text,
                onValueChange = { text = it },
                label = {Text(stringResource(id = R.string.search))},

                )
        }
    }
}

@Preview
@Composable
fun SearchBarPreview() {
    TranslatorTheme { SearchBar {} }
}