package com.example.book.presentation.screen.library.data

import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Immutable
import androidx.compose.ui.focus.FocusRequester
import com.example.book.data.model.Book
import com.example.book.data.model.Category
import com.example.book.data.util.OnNavigate
import com.example.book.data.util.Selected


@Immutable
sealed class LibraryEvent {
    data object OnRefreshList : LibraryEvent()
    data object OnLoadList : LibraryEvent()
    data class OnRecalculateCategories(val books: List<Pair<Book, Selected>>) : LibraryEvent()
    data class OnScrollToPage(val index: Int, val pagerState: PagerState) : LibraryEvent()
    data class OnUpdateCurrentPage(val page: Int) : LibraryEvent()
    data object OnSearchShowHide : LibraryEvent()
    data class OnRequestFocus(val focusRequester: FocusRequester) : LibraryEvent()
    data class OnSearchQueryChange(val query: String) : LibraryEvent()
    data object OnSearch : LibraryEvent()
    data class OnSelectBook(val book: Pair<Book, Selected>, val select: Boolean? = null) :
        LibraryEvent()

    data object OnClearSelectedBooks : LibraryEvent()
    data object OnShowHideMoveDialog : LibraryEvent()
    data class OnSelectCategory(val category: Category) : LibraryEvent()
    data class OnMoveBooks(val pagerState: PagerState, val refreshList: () -> Unit) : LibraryEvent()
    data object OnShowHideDeleteDialog : LibraryEvent()
    data class OnDeleteBooks(val refreshList: () -> Unit) : LibraryEvent()
    data class OnUpdateBook(val book: Book) : LibraryEvent()
    data class OnNavigateToReaderScreen(
        val onNavigate: OnNavigate,
        val book: Book
    ) : LibraryEvent()
}