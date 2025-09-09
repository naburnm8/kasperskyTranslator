@file:Suppress("FunctionName")

package ru.bmstu.naburnm8.translator.ui.translate



import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import ru.bmstu.naburnm8.translator.R
import ru.bmstu.naburnm8.translator.domain.WordDomain
import ru.bmstu.naburnm8.translator.ui.history.HistoryPeekCard
import ru.bmstu.naburnm8.translator.ui.theme.TranslatorTheme
import ru.bmstu.naburnm8.translator.viewModel.history.HistoryViewModel
import ru.bmstu.naburnm8.translator.viewModel.translate.TranslateState

import ru.bmstu.naburnm8.translator.viewModel.translate.TranslateViewModel

@Composable
fun TranslateScreen(
    vmNetwork: TranslateViewModel = koinViewModel(),
    vmDatabase: HistoryViewModel = koinViewModel(),
) {
    val state = vmNetwork.stateFlow.collectAsState()
    val dbItems by vmDatabase.stateFlow.collectAsStateWithLifecycle()

    Column (modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
        Spacer(modifier = Modifier.height(24.dp))
        TranslateContent(
                modifier = Modifier.padding(16.dp),
                onSearchButtonClick = { vmNetwork.search(it) },
                state = state.value,
            )
        HistoryPeekCard(
            modifier = Modifier.fillMaxSize().weight(0.7f).padding(16.dp),
            list = dbItems,
            onFavouriteToggle = { vmDatabase.toggleFavourite(it) },
            onDeleteClick = {vmDatabase.delete(it)}
        )
    }

}



@Composable
fun TranslateContent(
    modifier: Modifier = Modifier,
    state: TranslateState,
    onSearchButtonClick: (String) -> Unit,
    validateInput: (String) -> Boolean = { true },
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SearchBar(
            onSearchButtonClick = onSearchButtonClick,
            validateInput = validateInput
        )

        when (state) {
            is TranslateState.Success -> {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth().padding(4.dp).shadow(
                        elevation = 20.dp,
                        shape = RoundedCornerShape(30.dp),
                        ambientColor = MaterialTheme.colorScheme.primary,
                        spotColor = MaterialTheme.colorScheme.primary
                    ).background(MaterialTheme.colorScheme.onBackground),


                ) {
                    Row (
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth().weight(0.75f)
                    ) {
                        Text(
                            text = state.word.word,
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 24.sp
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.arrow),
                            contentDescription = null,
                            modifier = Modifier.rotate(-90f).size(48.dp),
                            tint = MaterialTheme.colorScheme.primary,
                        )
                        Text(
                            text = state.word.translation,
                            color = MaterialTheme.colorScheme.secondary,
                            fontSize = 24.sp
                        )
                    }

                }
            }
            TranslateState.Error -> {
                StyledBox {
                        Text(
                            text = stringResource(id = R.string.error),
                            modifier = Modifier.fillMaxWidth(),
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary,
                            textAlign = TextAlign.Center
                        )

                    }

            }
            TranslateState.Loading -> {
                StyledBox {
                    LinearProgressIndicator(
                        modifier = Modifier.padding(top = 12.dp, bottom = 12.dp).fillMaxWidth().padding(start = 8.dp, end = 8.dp),
                    )

                }
            }
            TranslateState.Empty -> {
                StyledBox {
                    Text(
                        text = stringResource(id = R.string.tryTranslate),
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                }
            }
            TranslateState.NoTranslation -> {
                StyledBox {
                    Text(
                        text = stringResource(id = R.string.noTranslation),
                        modifier = Modifier.fillMaxWidth(),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }

}

@Composable
fun StyledBox(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
){
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.fillMaxWidth().padding(4.dp).shadow(
            elevation = 20.dp,
            shape = RoundedCornerShape(30.dp),
            ambientColor = MaterialTheme.colorScheme.primary,
            spotColor = MaterialTheme.colorScheme.primary
        )
            .background(MaterialTheme.colorScheme.onBackground),
    ) {
        content()
    }
}

@Preview
@Composable
fun TranslateContentPreviewEasyState() {
    TranslatorTheme {
        TranslateContent(
            onSearchButtonClick = {},
            state = TranslateState.Error,
            modifier = Modifier,
        )
    }
}

@Preview
@Composable
fun TranslateContentPreviewComplicatedState() {
    TranslatorTheme {
        TranslateContent(
            state = TranslateState.Success(
                WordDomain(
                    word = "Cotton",
                    translation = "Хлопок",
                )
            ),
            onSearchButtonClick = {},
        )
    }
}
