package com.example.book.presentation.screen.browse.components.layout

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.example.book.data.model.SelectableFile
import com.example.book.presentation.main.MainState

import com.example.book.presentation.screen.browse.components.layout.grid.BrowseGridLayout
import com.example.book.presentation.screen.browse.components.layout.list.BrowseListLayout
import com.example.book.presentation.screen.browse.data.BrowseState
import com.example.book.presentation.screen.settings.nested.browse.data.BrowseLayout

/**
 * Browse Layout.
 * Shows Grid or List.
 *
 * @param state [BrowseState].
 * @param mainState [MainState].
 * @param filteredFiles Filtered items.
 * @param onLongItemClick OnLongItemClick callback.
 * @param onFavoriteItemClick OnFavoriteItemClick callback.
 * @param onItemClick OnItemClick callback.
 */
@Composable
fun BrowseLayout(
    state: State<BrowseState>,
    mainState: State<MainState>,
    filteredFiles: List<SelectableFile>,
    onLongItemClick: (SelectableFile) -> Unit,
    onFavoriteItemClick: (SelectableFile) -> Unit,
    onItemClick: (SelectableFile) -> Unit
) {
    when (mainState.value.browseLayout!!) {
        BrowseLayout.LIST -> {
            BrowseListLayout(
                state = state,
                filteredFiles = filteredFiles,
                onLongItemClick = onLongItemClick,
                onFavoriteItemClick = onFavoriteItemClick,
                onItemClick = onItemClick
            )
        }

        BrowseLayout.GRID -> {
            BrowseGridLayout(
                state = state,
                gridSize = mainState.value.browseGridSize!!,
                autoGridSize = mainState.value.browseAutoGridSize!!,
                filteredFiles = filteredFiles,
                onLongItemClick = onLongItemClick,
                onFavoriteItemClick = onFavoriteItemClick,
                onItemClick = onItemClick
            )
        }
    }
}