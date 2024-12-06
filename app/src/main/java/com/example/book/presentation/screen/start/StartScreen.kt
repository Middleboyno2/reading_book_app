@file:OptIn(ExperimentalPermissionsApi::class)

package com.example.book.presentation.screen.start

import android.Manifest
import android.annotation.SuppressLint
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.book.presentation.main.MainEvent
import com.example.book.presentation.main.MainState
import com.example.book.presentation.main.MainViewModel
import com.example.book.presentation.screen.browse.data.BrowseEvent
import com.example.book.presentation.screen.browse.data.BrowseViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.rememberPermissionState

import com.example.book.presentation.screen.start.components.StartDone
import com.example.book.presentation.screen.start.components.StartSettings
import com.example.book.presentation.screen.start.data.StartEvent
import com.example.book.presentation.screen.start.data.StartState
import com.example.book.presentation.screen.start.data.StartViewModel

@SuppressLint("InlinedApi")
@Composable
fun StartScreenRoot() {
    val startViewModel: StartViewModel = hiltViewModel()
    val mainViewModel: MainViewModel = hiltViewModel()
    val browseViewModel: BrowseViewModel = hiltViewModel()

    val state = startViewModel.state.collectAsState()
    val mainState = mainViewModel.state.collectAsState()
    val storagePermissionState = rememberPermissionState(
        permission = Manifest.permission.READ_EXTERNAL_STORAGE
    )
    val notificationsPermissionState = rememberPermissionState(
        permission = Manifest.permission.POST_NOTIFICATIONS
    )

    LaunchedEffect(Unit) {
        startViewModel.checkPermissions(
            storagePermissionState = storagePermissionState,
            notificationPermissionState = notificationsPermissionState
        )
    }

    StartScreen(
        state = state,
        mainState = mainState,
        storagePermissionState = storagePermissionState,
        notificationsPermissionState = notificationsPermissionState,
        onEvent = startViewModel::onEvent,
        onMainEvent = mainViewModel::onEvent,
        onBrowseEvent = browseViewModel::onEvent
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "InlinedApi")
@Composable
private fun StartScreen(
    state: State<StartState>,
    mainState: State<MainState>,
    storagePermissionState: PermissionState,
    notificationsPermissionState: PermissionState,
    onEvent: (StartEvent) -> Unit,
    onMainEvent: (MainEvent) -> Unit,
    onBrowseEvent: (BrowseEvent) -> Unit,
) {
    val activity = LocalContext.current as ComponentActivity

    // Settings
    StartSettings(
        state = state,
        mainState = mainState,
        onEvent = onEvent,
        onMainEvent = onMainEvent,
        storagePermissionState = storagePermissionState,
        notificationsPermissionState = notificationsPermissionState
    )

    // Done
    StartDone(
        state = state,
        onEvent = onEvent,
        onMainEvent = onMainEvent,
        onBrowseEvent = onBrowseEvent
    )

    BackHandler {
        onEvent(
            StartEvent.OnGoBack {
                activity.finish()
            }
        )
    }
}