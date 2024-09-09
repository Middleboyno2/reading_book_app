package com.example.book.data.model

import android.net.Uri
import androidx.compose.runtime.Immutable
import com.example.book.data.util.UIText


@Immutable
data class Book(
    val id: Int = 0,

    val title: String,
    val author: UIText,
    val description: String?,

    val textPath: String,
    val filePath: String,
    val coverImage: Uri?,

    val scrollIndex: Int,
    val scrollOffset: Int,
    val progress: Float,

    val lastOpened: Long?,
    val category: Category,
)
