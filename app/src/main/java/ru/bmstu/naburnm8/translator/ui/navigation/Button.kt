@file:Suppress("FunctionName")

package ru.bmstu.naburnm8.translator.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.draw.shadow

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.bmstu.naburnm8.translator.R
import ru.bmstu.naburnm8.translator.ui.theme.TranslatorTheme

@Composable
fun BaseButton(
    modifier: Modifier = Modifier,
    painter: Painter,
    contentDescription: String,
    isSelected: Boolean = false,
    onClick: () -> Unit,
) {
    val mainColor = if (isSelected) MaterialTheme.colorScheme.secondary else MaterialTheme.colorScheme.primary
    Column (
        modifier = modifier
            .padding(4.dp)
            .shadow(
                elevation = if (isSelected) 20.dp else 10.dp,
                clip = true,
                shape = RoundedCornerShape(10.dp),
                spotColor = mainColor,
                ambientColor = mainColor
            )
            .background(MaterialTheme.colorScheme.onBackground)
            .height(90.dp)
            .width(90.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = onClick,
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically)
        ) {
            Icon(
                painter = painter,
                contentDescription = contentDescription,
                tint = mainColor,
                modifier = Modifier.fillMaxSize()
            )
        }

        Text (
            text = contentDescription,
            textAlign = TextAlign.Center,
            color = mainColor,
            fontSize = 14.sp,
            modifier = Modifier.wrapContentSize()
        )
    }
}

@Preview
@Composable
fun BaseButtonPreviewLight() {
    TranslatorTheme(darkTheme = false) {
        BaseButton(
            painter = painterResource(id = R.drawable.translate),
            contentDescription = "Translate",
            onClick = {}
        )
    }
}

@Preview
@Composable
fun BaseButtonPreviewDark() {
    TranslatorTheme(darkTheme = true) {
        BaseButton(
            painter = painterResource(id = R.drawable.translate),
            contentDescription = "Translate",
            onClick = {}
        )
    }
}

@Preview
@Composable
fun BaseButtonPreviewLightClicked() {
    TranslatorTheme(darkTheme = false) {
        BaseButton(
            painter = painterResource(id = R.drawable.translate),
            contentDescription = "Translate",
            onClick = {},
            isSelected = true
        )
    }
}

@Preview
@Composable
fun BaseButtonPreviewDarkClicked() {
    TranslatorTheme(darkTheme = true) {
        BaseButton(
            painter = painterResource(id = R.drawable.translate),
            contentDescription = "Translate",
            onClick = {},
            isSelected = true
        )
    }
}