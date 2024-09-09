package com.example.book.presentation.screen.settings.nested.reader.components.settings

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
import com.example.book.presentation.screen.settings.nested.reader.data.ReaderTextAlignment

/**
 * Text Alignment setting.
 * Changes Reader's text alignment (Start/Justify/Center/End).
 */
@Composable
fun LazyItemScope.TextAlignmentSetting(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit
) {
    SegmentedButtonWithTitle(
        title = stringResource(id = R.string.text_alignment_option),
        modifier = Modifier.animateItem(),
        buttons = ReaderTextAlignment.entries.map {
            ButtonItem(
                id = it.toString(),
                title = when (it) {
                    ReaderTextAlignment.START -> stringResource(id = R.string.text_alignment_start)
                    ReaderTextAlignment.JUSTIFY -> stringResource(id = R.string.text_alignment_justify)
                    ReaderTextAlignment.CENTER -> stringResource(id = R.string.text_alignment_center)
                    ReaderTextAlignment.END -> stringResource(id = R.string.text_alignment_end)
                },
                textStyle = MaterialTheme.typography.labelLarge,
                selected = it == state.value.textAlignment!!
            )
        },
        onClick = {
            onMainEvent(
                MainEvent.OnChangeTextAlignment(
                    it.id
                )
            )
        }
    )
}