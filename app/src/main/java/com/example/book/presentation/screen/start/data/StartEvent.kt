@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.book.presentation.screen.start.data

import androidx.activity.ComponentActivity
import androidx.compose.runtime.Immutable
import com.example.book.data.util.OnNavigate
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState


@Immutable
sealed class StartEvent {
    data class OnGoBack(val onQuit: () -> Unit) : StartEvent()
    data object OnGoForward : StartEvent()
    data class OnStoragePermissionRequest(
        val activity: ComponentActivity,
        val legacyStoragePermissionState: PermissionState
    ) : StartEvent()

    data class OnNotificationsPermissionRequest(
        val activity: ComponentActivity,
        val notificationsPermissionState: PermissionState
    ) : StartEvent()

    data class OnGoToBrowse(val onNavigate: OnNavigate, val onCompletedStartGuide: () -> Unit) :
        StartEvent()

    data class OnGoToHelp(val onNavigate: OnNavigate) : StartEvent()

    data object OnResetStartScreen : StartEvent()
}







