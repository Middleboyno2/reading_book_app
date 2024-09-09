package com.example.book.presentation.screen.history.data

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Immutable
import com.example.book.data.model.GroupedHistory


@Immutable
data class HistoryState(
    val history: List<GroupedHistory> = emptyList(),
    val listState: LazyListState = LazyListState(0, 0),

    val isRefreshing: Boolean = false,
    val isLoading: Boolean = true,

    val showDeleteWholeHistoryDialog: Boolean = false,

    val showSearch: Boolean = false,
    val searchQuery: String = "",
    val hasFocused: Boolean = false,
)
