package com.example.book.presentation.screen.settings.nested.reader.components.settings

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.book.R
import com.example.book.presentation.main.MainEvent
import com.example.book.presentation.main.MainState

import com.example.book.presentation.screen.settings.components.SliderWithTitle

/**
 * Line Height setting.
 * Changes Reader's line height.
 */
@Composable
fun LazyItemScope.LineHeightSetting(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit
) {
    SliderWithTitle(
        value = state.value.lineHeight!! to "pt",
        modifier = Modifier.animateItem(),
        fromValue = 1,
        toValue = 16,
        title = stringResource(id = R.string.line_height_option),
        onValueChange = {
            onMainEvent(
                MainEvent.OnChangeLineHeight(it)
            )
        }
    )
}