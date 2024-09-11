package com.example.book.presentation.screen.history.components

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.book.R
import com.example.book.presentation.components.custom_dialog.CustomDialogWithContent

import com.example.book.presentation.screen.history.data.HistoryEvent
import com.example.book.presentation.screen.library.data.LibraryEvent

/**
 * Delete whole history dialog.
 */
@Composable
fun HistoryDeleteWholeHistoryDialog(
    onEvent: (HistoryEvent) -> Unit,
    onLibraryLoadEvent: (LibraryEvent.OnLoadList) -> Unit
) {
    val context = LocalContext.current

    CustomDialogWithContent(
        title = stringResource(id = R.string.delete_history),
        imageVectorIcon = Icons.Outlined.DeleteOutline,
        description = stringResource(
            id = R.string.delete_history_description
        ),
        actionText = stringResource(id = R.string.delete),
        onDismiss = { onEvent(HistoryEvent.OnShowHideDeleteWholeHistoryDialog) },
        withDivider = false,
        isActionEnabled = true,
        onAction = {
            onEvent(
                HistoryEvent.OnDeleteWholeHistory {
                    onLibraryLoadEvent(LibraryEvent.OnLoadList)
                }
            )
            Toast.makeText(
                context,
                context.getString(R.string.history_deleted),
                Toast.LENGTH_LONG
            ).show()
        }
    )
}