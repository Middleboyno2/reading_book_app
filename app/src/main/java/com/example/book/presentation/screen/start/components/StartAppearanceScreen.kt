package com.example.book.presentation.screen.start.components

import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.State
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.book.R
import com.example.book.presentation.main.MainEvent
import com.example.book.presentation.main.MainState

import com.example.book.presentation.screen.settings.nested.appearance.components.subcategories.ThemePreferencesSubcategory

/**
 * Appearance settings.
 */
fun LazyListScope.startAppearanceScreen(
    mainState: State<MainState>,
    onMainEvent: (MainEvent) -> Unit
) {
    ThemePreferencesSubcategory(
        state = mainState,
        onMainEvent = onMainEvent,
        title = { stringResource(id = R.string.start_theme_preferences) },
        topPadding = 16.dp,
        bottomPadding = 8.dp
    )
}