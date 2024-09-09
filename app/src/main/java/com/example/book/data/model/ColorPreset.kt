package com.example.book.data.model

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import com.example.book.data.util.Selected

@Immutable
data class ColorPreset(
    val id: Int,
    val name: String?,
    val backgroundColor: Color,
    val fontColor: Color,
    val isSelected: Selected
)