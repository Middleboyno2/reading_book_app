package com.example.book.presentation.screen.browse.components.layout.grid

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.book.data.model.SelectableFile
import com.example.book.presentation.components.custom.customItems
import com.example.book.presentation.components.custom.header

import com.example.book.presentation.screen.browse.data.BrowseState
import com.example.book.presentation.screen.settings.nested.browse.data.BrowseLayout
import com.example.book.presentation.screen.browse.components.layout.BrowseItem

@Composable
fun BrowseGridLayout(
    state: State<BrowseState>,
    gridSize: Int,
    autoGridSize: Boolean,
    filteredFiles: List<SelectableFile>,
    onLongItemClick: (SelectableFile) -> Unit,
    onFavoriteItemClick: (SelectableFile) -> Unit,
    onItemClick: (SelectableFile) -> Unit,
) {
    LazyVerticalGrid(
        columns = if (autoGridSize) GridCells.Adaptive(170.dp)
        else GridCells.Fixed(gridSize.coerceAtLeast(1)),
        state = state.value.gridState,
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(horizontal = 8.dp)
    ) {
        header {
            Spacer(modifier = Modifier.height(8.dp))
        }

        customItems(
            filteredFiles,
            key = { it.fileOrDirectory.path }
        ) { selectableFile ->
            BrowseItem(
                file = selectableFile,
                modifier = Modifier.animateItem(),
                layout = BrowseLayout.GRID,
                hasSelectedFiles = state.value.selectableFiles.any { it.isSelected },
                onLongClick = {
                    onLongItemClick(selectableFile)
                },
                onFavoriteClick = {
                    onFavoriteItemClick(selectableFile)
                },
                onClick = {
                    onItemClick(selectableFile)
                }
            )
        }

        header {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}