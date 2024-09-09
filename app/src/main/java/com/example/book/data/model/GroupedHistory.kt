package com.example.book.data.model

import androidx.compose.runtime.Immutable

@Immutable
data class GroupedHistory(
    val title: String,
    val history: List<History>
)
