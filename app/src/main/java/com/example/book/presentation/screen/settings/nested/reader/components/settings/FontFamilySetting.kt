package com.example.book.presentation.screen.settings.nested.reader.components.settings

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.book.R
import com.example.book.data.model.ButtonItem
import com.example.book.data.util.Constants
import com.example.book.presentation.main.MainEvent
import com.example.book.presentation.main.MainState

import com.example.book.presentation.screen.settings.components.ChipsWithTitle

/**
 * Font Family setting.
 * Changes Reader's font, uses fonts from [Constants.FONTS].
 */
@Composable
fun LazyItemScope.FontFamilySetting(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit
) {
    val fontFamily = remember(state.value.fontFamily) {
        Constants.FONTS.find {
            it.id == state.value.fontFamily
        } ?: Constants.FONTS[0]
    }

    ChipsWithTitle(
        title = stringResource(id = R.string.font_family_option),
        modifier = Modifier.animateItem(),
        chips = Constants.FONTS
            .map {
                ButtonItem(
                    id = it.id,
                    title = it.fontName.asString(),
                    textStyle = MaterialTheme.typography.labelLarge.copy(
                        fontFamily = it.font
                    ),
                    selected = it.id == fontFamily.id
                )
            },
        onClick = {
            onMainEvent(MainEvent.OnChangeFontFamily(it.id))
        }
    )
}