@file:Suppress("FunctionName")

package com.example.book.presentation.screen.settings.nested.appearance.components.subcategories

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.coerceAtLeast
import androidx.compose.ui.unit.dp
import com.example.book.R
import com.example.book.presentation.components.custom.CategoryTitle
import com.example.book.presentation.main.MainEvent
import com.example.book.presentation.main.MainState

import com.example.book.presentation.screen.settings.data.SettingsEvent
import com.example.book.presentation.screen.settings.data.SettingsState
import com.example.book.presentation.screen.settings.nested.appearance.components.settings.ColorPresetSetting
import com.example.book.presentation.screen.settings.nested.appearance.components.settings.FastColorPresetChangeSetting

/**
 * Colors subcategory.
 * Contains all settings from Colors.
 */
fun LazyListScope.ColorsSubcategory(
    state: State<MainState>,
    settingsState: State<SettingsState>,
    onMainEvent: (MainEvent) -> Unit,
    onSettingsEvent: (SettingsEvent) -> Unit,
    titleColor: @Composable () -> Color = { MaterialTheme.colorScheme.primary },
    title: @Composable () -> String = { stringResource(id = R.string.colors_appearance_settings) },
    backgroundColor: @Composable () -> Color = { MaterialTheme.colorScheme.surfaceContainerLow },
    showTitle: Boolean = true,
    topPadding: Dp,
    bottomPadding: Dp
) {
    item {
        if (showTitle) {
            CategoryTitle(
                title = title.invoke(),
                color = titleColor.invoke(),
                modifier = Modifier
                    .animateItem()
                    .padding(
                        top = topPadding,
                        bottom = 8.dp
                    )
            )
        } else {
            Spacer(
                modifier = Modifier
                    .animateItem()
                    .height((topPadding - 8.dp).coerceAtLeast(0.dp))
            )
        }
    }

    item {
        ColorPresetSetting(
            state = settingsState,
            backgroundColor = backgroundColor.invoke(),
            onEvent = onSettingsEvent
        )
    }

    item {
        FastColorPresetChangeSetting(
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