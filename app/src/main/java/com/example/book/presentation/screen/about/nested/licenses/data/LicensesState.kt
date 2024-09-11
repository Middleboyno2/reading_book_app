package com.example.book.presentation.screen.about.nested.licenses.data

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Immutable
import com.mikepenz.aboutlibraries.entity.Library

@Immutable
data class LicensesState(
    val licenses: List<Library> = emptyList(),
    val listState: LazyListState = LazyListState()
)
