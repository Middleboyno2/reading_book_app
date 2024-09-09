package com.example.book.presentation.screen.settings.nested.browse.components.settings

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
import com.example.book.presentation.screen.settings.nested.browse.data.BrowseLayout

/**
 * Browse Layout setting.
 * Lets user choose between layouts(list, grid).
 */
@Composable
fun LazyItemScope.BrowseLayoutSetting(
    state: State<MainState>,
    onMainEvent: (MainEvent) -> Unit
) {
    SegmentedButtonWithTitle(
        title = stringResource(id = R.string.browse_layout_option),
        modifier = Modifier.animateItem(),
        buttons = BrowseLayout.entries.map {
            ButtonItem(
                it.toString(),
                when (it) {
                    BrowseLayout.LIST -> stringResource(id = R.string.browse_layout_list)
                    BrowseLayout.GRID -> stringResource(id = R.string.browse_layout_grid)
                },
                MaterialTheme.typography.labelLarge,
                it == state.value.browseLayout
            )
        }
    ) {
        onMainEvent(
            MainEvent.OnChangeBrowseLayout(
                it.id
            )
        )
    }
}