package com.example.book.presentation.screen.settings.nested.reader.components.settings

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.book.R
import com.example.book.presentation.main.MainEvent
import com.example.book.presentation.main.MainState
import com.example.book.presentation.screen.settings.components.SwitchWithTitle


/**
 * Double Click Translation setting.
 * Changes Reader's double click translation.
 */
@Composable
fun LazyItemScope.DoubleClickTranslationSetting(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit
) {
    SwitchWithTitle(
        selected = state.value.doubleClickTranslation!!,
        modifier = Modifier.animateItem(),
        title = stringResource(id = R.string.double_click_translation_option),
        description = stringResource(id = R.string.double_click_translation_option_desc),
        onClick = {
            onMainEvent(
                MainEvent.OnChangeDoubleClickTranslation(
                    !state.value.doubleClickTranslation!!
                )
            )
        }
    )
}