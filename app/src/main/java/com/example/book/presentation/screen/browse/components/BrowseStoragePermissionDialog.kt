package com.example.book.presentation.screen.browse.components

import androidx.activity.ComponentActivity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SdStorage
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.book.R
import com.example.book.presentation.components.custom_dialog.CustomDialogWithContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState

import com.example.book.presentation.screen.browse.data.BrowseEvent

/**
 * Storage permission dialog.
 *
 * @param onEvent [BrowseEvent] callback.
 * @param permissionState Storage [PermissionState].
 */
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun BrowseStoragePermissionDialog(
    onEvent: (BrowseEvent) -> Unit,
    permissionState: PermissionState
) {
    val activity = LocalContext.current as ComponentActivity

    CustomDialogWithContent(
        title = stringResource(id = R.string.storage_permission),
        description = stringResource(id = R.string.storage_permission_description),
        actionText = stringResource(id = R.string.grant),
        imageVectorIcon = Icons.Default.SdStorage,
        onDismiss = {
            onEvent(
                BrowseEvent.OnStoragePermissionDismiss(
                    permissionState = permissionState
                )
            )
        },
        isActionEnabled = true,
        withDivider = false,
        disableOnClick = false,
        onAction = {
            onEvent(
                BrowseEvent.OnStoragePermissionRequest(
                    activity = activity,
                    storagePermissionState = permissionState
                )
            )
        }
    )
}