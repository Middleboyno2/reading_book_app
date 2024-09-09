@file:Suppress("FunctionName")

package com.example.book.presentation.screen.settings.nested.general.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.example.book.presentation.main.MainEvent
import com.example.book.presentation.main.MainState

import com.example.book.presentation.screen.settings.data.SettingsEvent
import com.example.book.presentation.screen.settings.nested.general.components.settings.AppLanguageSetting


/**
 * General Settings Category.
 * Contains all General settings such as App Language.
 *
 * @param state [MainState] instance.
 * @param onMainEvent [MainEvent] callback.
 * @param onSettingsEvent [SettingsEvent] callback.
 * @param topPadding Top padding to be applied.
 * @param bottomPadding Bottom padding to be applied.
 */
fun LazyListScope.GeneralSettingsCategory(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit,
    onSettingsEvent: (SettingsEvent) -> Unit,
    topPadding: Dp = 16.dp,
    bottomPadding: Dp = 48.dp
) {
    item {
        Spacer(
            modifier = Modifier
                .animateItem()
                .height((topPadding - 8.dp).coerceAtLeast(0.dp))
        )
    }

    item {
        AppLanguageSetting(
            state = state,
            onMainEvent = onMainEvent
        )
    }



    item {
        Spacer(
            modifier = Modifier
                .animateItem()
                .height(bottomPadding)
        )
    }
}