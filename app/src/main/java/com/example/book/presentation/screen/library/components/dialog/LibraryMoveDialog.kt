package com.example.book.presentation.screen.library.components.dialog

import android.widget.Toast
import androidx.compose.foundation.pager.PagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.DriveFileMove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.book.R
import com.example.book.data.model.Category
import com.example.book.presentation.components.custom.customItems
import com.example.book.presentation.components.custom_dialog.CustomDialogWithLazyColumn
import com.example.book.presentation.components.custom_dialog.SelectableDialogItem

import com.example.book.presentation.screen.history.data.HistoryEvent
import com.example.book.presentation.screen.library.data.LibraryEvent
import com.example.book.presentation.screen.library.data.LibraryState

/**
 * Move dialog. Moves all selected books to the selected category.
 */
@Composable
fun LibraryMoveDialog(
    state: State<LibraryState>,
    onEvent: (LibraryEvent) -> Unit,
    onHistoryLoadEvent: (HistoryEvent.OnLoadList) -> Unit,
    pagerState: PagerState
) {
    val context = LocalContext.current

    CustomDialogWithLazyColumn(
        title = stringResource(id = R.string.move_books),
        imageVectorIcon = Icons.AutoMirrored.Outlined.DriveFileMove,
        description = stringResource(
            id = R.string.move_books_description,
            state.value.books.filter { it.second }.size
        ),
        actionText = stringResource(id = R.string.move),
        isActionEnabled = true,
        onDismiss = { onEvent(LibraryEvent.OnShowHideMoveDialog) },
        onAction = {
            onEvent(
                LibraryEvent.OnMoveBooks(
                    pagerState,
                    refreshList = {
                        onHistoryLoadEvent(HistoryEvent.OnLoadList)
                    }
                )
            )
            Toast.makeText(
                context,
                context.getString(R.string.books_moved),
                Toast.LENGTH_LONG
            ).show()
        },
        withDivider = false,
        items = {
            customItems(state.value.categories, key = { it.name }) {
                val category = when (it) {
                    Category.READING -> stringResource(id = R.string.reading_tab)
                    Category.ALREADY_READ -> stringResource(id = R.string.already_read_tab)

                }

                SelectableDialogItem(
                    selected = it == state.value.selectedCategory,
                    title = category
                ) {
                    onEvent(LibraryEvent.OnSelectCategory(it))
                }
            }
        }
    )
}