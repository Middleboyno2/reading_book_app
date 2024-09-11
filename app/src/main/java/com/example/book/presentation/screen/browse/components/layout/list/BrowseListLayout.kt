package com.example.book.presentation.screen.browse.components.layout.list

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.book.data.model.SelectableFile
import com.example.book.presentation.components.custom.customItems

import com.example.book.presentation.screen.browse.components.layout.BrowseItem
import com.example.book.presentation.screen.browse.data.BrowseState
import com.example.book.presentation.screen.settings.nested.browse.data.BrowseLayout

@Composable
fun BrowseListLayout(
    state: State<BrowseState>,
    filteredFiles: List<SelectableFile>,
    onLongItemClick: (SelectableFile) -> Unit,
    onFavoriteItemClick: (SelectableFile) -> Unit,
    onItemClick: (SelectableFile) -> Unit,
) {
    LazyColumn(
        state = state.value.listState,
        modifier = Modifier
            .fillMaxSize()
    ) {
        item {
            Spacer(modifier = Modifier.height(8.dp))
        }

        customItems(
            filteredFiles,
            key = { it.fileOrDirectory.path }
        ) { selectableFile ->
            BrowseItem(
                file = selectableFile,
                layout = BrowseLayout.LIST,
                modifier = Modifier.animateItem(),
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

        item {
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}