package com.example.book.presentation.screen.about.nested.license_info.data

import androidx.compose.runtime.Immutable
import com.example.book.presentation.main.Screen
import com.mikepenz.aboutlibraries.entity.Library


@Immutable
data class LicenseInfoState(
    val license: Library? = null,
)