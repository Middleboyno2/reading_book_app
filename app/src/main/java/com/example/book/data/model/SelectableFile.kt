package com.example.book.data.model

import androidx.compose.runtime.Immutable
import com.example.book.data.util.Selected
import java.io.File

@Immutable
data class SelectableFile(
    val fileOrDirectory: File,
    val parentDirectory: File,
    val isDirectory: Boolean,
    val isFavorite: Boolean,
    val isSelected: Selected
)