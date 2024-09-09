package com.example.book.data.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.font.FontFamily
import com.example.book.data.util.UIText

@Immutable
data class FontWithName(
    val id: String,
    val fontName: UIText,
    val font: FontFamily
)