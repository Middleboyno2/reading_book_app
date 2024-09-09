package com.example.book.presentation.screen.settings.nested.appearance.components.settings

import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.book.R
import com.example.book.data.model.ButtonItem
import com.example.book.presentation.main.MainEvent
import com.example.book.presentation.main.MainState
import com.example.book.presentation.screen.settings.components.SegmentedButtonWithTitle
import com.example.book.ui.theme.PureDark
import com.example.book.ui.theme.SlidingTransition
import com.example.book.ui.theme.isDark


/**
 * Pure Dark setting.
 * If true, enables Pure Dark (OLED) theme.
 */
@Composable
fun LazyItemScope.PureDarkSetting(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit
) {
    SlidingTransition(
        modifier = Modifier.animateItem(
            fadeInSpec = null,
            fadeOutSpec = null
        ),
        visible = state.value.darkTheme!!.isDark(),
    ) {
        SegmentedButtonWithTitle(
            title = stringResource(id = R.string.pure_dark_option),
            buttons = PureDark.entries.map {
                ButtonItem(
                    id = it.toString(),
                    title = when (it) {
                        PureDark.OFF -> stringResource(id = R.string.pure_dark_off)
                        PureDark.ON -> stringResource(id = R.string.pure_dark_on)
                        PureDark.SAVER -> stringResource(id = R.string.pure_dark_power_saver)
                    },
                    textStyle = MaterialTheme.typography.labelLarge,
                    selected = it == state.value.pureDark
                )
            }
        ) {
            onMainEvent(
                MainEvent.OnChangePureDark(
                    it.id
                )
            )
        }
    }
}