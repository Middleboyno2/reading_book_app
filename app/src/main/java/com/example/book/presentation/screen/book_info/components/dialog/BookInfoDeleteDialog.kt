package com.example.book.presentation.screen.book_info.components.dialog

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.book.R
import com.example.book.presentation.components.custom_dialog.CustomDialogWithContent
import com.example.book.presentation.main.LocalNavigator

import com.example.book.presentation.screen.book_info.data.BookInfoEvent
import com.example.book.presentation.screen.browse.data.BrowseEvent
import com.example.book.presentation.screen.history.data.HistoryEvent
import com.example.book.presentation.screen.library.data.LibraryEvent

/**
 * Delete dialog. Deletes current book.
 *
 * @param onEvent [BookInfoEvent] callback.
 * @param onLibraryLoadEvent [LibraryEvent] callback.
 * @param onHistoryLoadEvent [HistoryEvent] callback.
 * @param onBrowseLoadEvent [BrowseEvent] callback.
 */
@Composable
fun BookInfoDeleteDialog(
    onEvent: (BookInfoEvent) -> Unit,
    onLibraryLoadEvent: (LibraryEvent.OnLoadList) -> Unit,
    onHistoryLoadEvent: (HistoryEvent.OnLoadList) -> Unit,
    onBrowseLoadEvent: (BrowseEvent.OnLoadList) -> Unit
) {
    val navigator = LocalNavigator.current
    val context = LocalContext.current

    CustomDialogWithContent(
        title = stringResource(id = R.string.delete_book),
        imageVectorIcon = Icons.Outlined.DeleteOutline,
        description = stringResource(
            id = R.string.delete_book_description
        ),
        actionText = stringResource(id = R.string.delete),
        onDismiss = { onEvent(BookInfoEvent.OnShowHideDeleteDialog) },
        withDivider = false,
        isActionEnabled = true,
        onAction = {
            onEvent(
                BookInfoEvent.OnDeleteBook(
                    onNavigate = { navigator.it() },
                    refreshList = {
                        onLibraryLoadEvent(LibraryEvent.OnLoadList)
                        onBrowseLoadEvent(BrowseEvent.OnLoadList)
                        onHistoryLoadEvent(HistoryEvent.OnLoadList)
                    }
                )
            )
            Toast.makeText(
                context,
                context.getString(R.string.book_deleted),
                Toast.LENGTH_LONG
            ).show()
        }
    )
}