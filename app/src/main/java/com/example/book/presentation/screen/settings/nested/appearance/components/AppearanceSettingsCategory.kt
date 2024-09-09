@file:Suppress("FunctionName")

package com.example.book.presentation.screen.settings.nested.appearance.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.book.presentation.main.MainEvent
import com.example.book.presentation.main.MainState

import com.example.book.presentation.screen.settings.data.SettingsEvent
import com.example.book.presentation.screen.settings.data.SettingsState
import com.example.book.presentation.screen.settings.nested.appearance.components.subcategories.ColorsSubcategory
import ua.acclorite.book_story.presentation.screens.settings.nested.appearance.components.subcategories.ThemePreferencesSubcategory

/**
 * Appearance Settings Category.
 * Contains all Appearance settings such as Theme.
 *
 * @param state [MainState] instance.
 * @param onMainEvent [MainEvent] callback.
 * @param onSettingsEvent [SettingsEvent] callback.
 * @param titleColor Title's color.
 * @param topPadding Top padding to be applied.
 * @param bottomPadding Bottom padding to be applied.
 */
fun LazyListScope.AppearanceSettingsCategory(
    state: State<MainState>,
    settingsState: State<SettingsState>,
    onMainEvent: (MainEvent) -> Unit,
    onSettingsEvent: (SettingsEvent) -> Unit,
    titleColor: @Composable () -> Color = { MaterialTheme.colorScheme.primary },
    topPadding: Dp = 16.dp,
    bottomPadding: Dp = 48.dp
) {
    ThemePreferencesSubcategory(
        state = state,
        onMainEvent = onMainEvent,
        titleColor = titleColor,
        topPadding = topPadding,
        bottomPadding = 0.dp
    )
    ColorsSubcategory(
        state = state,
        settingsState = settingsState,
        onMainEvent = onMainEvent,
        onSettingsEvent = onSettingsEvent,
        titleColor = titleColor,
        topPadding = 22.dp,
        bottomPadding = bottomPadding
    )
}