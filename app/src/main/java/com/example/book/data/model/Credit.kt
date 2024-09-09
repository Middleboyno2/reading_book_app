package com.example.book.data.model

import androidx.compose.runtime.Immutable
import com.example.book.data.util.UIText

@Immutable
data class Credit(
    val name: String,
    val source: String?,
    val credits: List<UIText>,
    val website: String?
)
