@file:Suppress("FunctionName")

package com.example.book.presentation.screen.settings.nested.reader.components.subcategories

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

import com.example.book.presentation.screen.settings.nested.reader.components.settings.LineHeightSetting
import com.example.book.presentation.screen.settings.nested.reader.components.settings.ParagraphHeightSetting
import com.example.book.presentation.screen.settings.nested.reader.components.settings.ParagraphIndentationSetting
import com.example.book.presentation.screen.settings.nested.reader.components.settings.SidePaddingSetting
import com.example.book.presentation.screen.settings.nested.reader.components.settings.TextAlignmentSetting

/**
 * Text subcategory.
 * Contains all settings from Text.
 */
fun LazyListScope.TextSubcategory(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit,
    titleColor: @Composable () -> Color = { MaterialTheme.colorScheme.primary },
    title: @Composable () -> String = { stringResource(id = R.string.text_reader_settings) },
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
        TextAlignmentSetting(
            state = state,
            onMainEvent = onMainEvent
        )
    }

    item {
        SidePaddingSetting(
            state = state,
            onMainEvent = onMainEvent
        )
    }

    item {
        LineHeightSetting(
            state = state,
            onMainEvent = onMainEvent
        )
    }

    item {
        ParagraphHeightSetting(
            state = state,
            onMainEvent = onMainEvent
        )
    }

    item {
        ParagraphIndentationSetting(
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