package com.example.book.presentation.screen.settings.nested.appearance.components.settings

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
 * Fast Color Preset Change setting.
 * If true, user can fast change color presets in Reader with swipes.
 */
@Composable
fun LazyItemScope.FastColorPresetChangeSetting(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit
) {
    SwitchWithTitle(
        selected = state.value.fastColorPresetChange!!,
        modifier = Modifier.animateItem(),
        title = stringResource(id = R.string.fast_color_preset_change_option),
        description = stringResource(id = R.string.fast_color_preset_change_option_desc),
        onClick = {
            onMainEvent(
                MainEvent.OnChangeFastColorPresetChange(
                    !state.value.fastColorPresetChange!!
                )
            )
        }
    )
}