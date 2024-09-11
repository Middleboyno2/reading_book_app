package com.example.book.presentation.screen.about.data

import androidx.compose.runtime.Immutable


@Immutable
data class AboutState(
    val showUpdateDialog: Boolean = false,
    val alreadyCheckedForUpdates: Boolean = false,
    val updateLoading: Boolean = false,
   
)
