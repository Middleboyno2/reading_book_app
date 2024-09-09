package com.example.book.data.model

import androidx.compose.runtime.Immutable
import com.example.book.data.util.Selected


@Immutable
enum class Category {
    READING, ALREADY_READ
}

@Immutable
data class CategorizedBooks(
    val category: Category,
    val books: List<Pair<Book, Selected>>
)