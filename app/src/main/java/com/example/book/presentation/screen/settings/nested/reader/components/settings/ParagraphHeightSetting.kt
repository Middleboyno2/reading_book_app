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
 * Paragraph Height setting.
 * Changes Reader's paragraph height.
 */
@Composable
fun LazyItemScope.ParagraphHeightSetting(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit
) {
    SliderWithTitle(
        value = state.value.paragraphHeight!! to "pt",
        modifier = Modifier.animateItem(),
        fromValue = 0,
        toValue = 24,
        title = stringResource(id = R.string.paragraph_height_option),
        onValueChange = {
            onMainEvent(
                MainEvent.OnChangeParagraphHeight(it)
            )
        }
    )
}