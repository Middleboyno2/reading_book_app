package com.example.book.data.model

import androidx.compose.runtime.Immutable
import com.example.book.data.model.Book

@Immutable
data class History(
    val id: Int = 0,
    val bookId: Int,
    val book: Book?,
    val time: Long
)