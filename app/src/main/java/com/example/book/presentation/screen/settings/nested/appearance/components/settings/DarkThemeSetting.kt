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
import com.example.book.ui.theme.DarkTheme


/**
 * Dark Theme setting.
 * If true, dark theme applied to the app's theme.
 */
@Composable
fun LazyItemScope.DarkThemeSetting(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit
) {
    SegmentedButtonWithTitle(
        title = stringResource(id = R.string.dark_theme_option),
        modifier = Modifier.animateItem(),
        buttons = DarkTheme.entries.map {
            ButtonItem(
                it.toString(),
                title = when (it) {
                    DarkTheme.OFF -> stringResource(id = R.string.dark_theme_off)
                    DarkTheme.ON -> stringResource(id = R.string.dark_theme_on)
                    DarkTheme.FOLLOW_SYSTEM -> stringResource(id = R.string.dark_theme_follow_system)
                },
                textStyle = MaterialTheme.typography.labelLarge,
                selected = it == state.value.darkTheme
            )
        }
    ) {
        onMainEvent(
            MainEvent.OnChangeDarkTheme(
                it.id
            )
        )
    }
}