package com.example.book.presentation.screen.about.data

import android.content.Context
import androidx.compose.runtime.Immutable

@Immutable
sealed class AboutEvent {
    data class OnNavigateToBrowserPage(
        val page: String,
        val context: Context,
        val noAppsFound: () -> Unit
    ) : AboutEvent()




}