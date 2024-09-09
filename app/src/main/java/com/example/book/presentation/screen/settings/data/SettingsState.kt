package com.example.book.presentation.screen.settings.data

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Immutable
import com.example.book.data.model.ColorPreset
import com.example.book.data.util.Constants


@Immutable
data class SettingsState(
    val colorPresets: List<ColorPreset> = emptyList(),
    val selectedColorPreset: ColorPreset = Constants.DEFAULT_COLOR_PRESET,
    val animateColorPreset: Boolean = false,
    val colorPresetsListState: LazyListState = LazyListState()
)