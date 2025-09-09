@file:Suppress("FunctionName")

package ru.bmstu.naburnm8.translator.ui.translate

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.ModifierLocalMap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.bmstu.naburnm8.translator.R
import ru.bmstu.naburnm8.translator.ui.theme.TranslatorTheme


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    onSearchButtonClick: (String) -> Unit,
    validateInput: (String) -> Boolean = { false },
) {
    var text by remember { mutableStateOf("") }

    val isError = validateInput(text)

    Row(modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
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
                .background(MaterialTheme.colorScheme.onBackground)
                .weight(0.8f),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            TextField(
                    value = text,
                    onValueChange = { text = it },
                    label = {if (isError) Text(stringResource(id = R.string.invalidInput)) else Text(stringResource(id = R.string.search))},
                    singleLine = true,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        errorContainerColor = Color.Transparent,
                        errorIndicatorColor = Color.Transparent,
                    ),
                isError = isError,
                )
        }
        IconButton (
            onClick = { onSearchButtonClick(text) },
            modifier = Modifier
                .weight(0.2f)
                .padding(start = 0.dp)
                .wrapContentSize()
                .shadow(
                    elevation = 20.dp,
                    clip = true,
                    shape = CircleShape,
                    spotColor = MaterialTheme.colorScheme.primary,
                    ambientColor = MaterialTheme.colorScheme.primary
                )
                .background(MaterialTheme.colorScheme.onBackground),
            //enabled = !isError
        ) {
            Icon(
                painter = if (isError) painterResource(R.drawable.validation_error) else painterResource(R.drawable.translate_btn),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
        }
    }
}

@Preview
@Composable
fun SearchBarPreviewLight() {
    TranslatorTheme { SearchBar(onSearchButtonClick = {  }) }
}

@Preview
@Composable
fun SearchBarPreviewDark() {
    TranslatorTheme (darkTheme = true) { SearchBar(onSearchButtonClick = {  }) }
}