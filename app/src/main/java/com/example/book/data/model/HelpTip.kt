package com.example.book.data.model

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.AnnotatedString
import com.example.book.data.util.OnNavigate
import com.example.book.presentation.screen.help.data.HelpEvent


@Immutable
data class HelpTip(
    @StringRes val title: Int,
    val description: @Composable AnnotatedString.Builder.(onNavigate: OnNavigate, fromStart: Boolean) -> Unit,
    val customContent: @Composable ColumnScope.(onHelpEvent: (HelpEvent) -> Unit) -> Unit = {},
)