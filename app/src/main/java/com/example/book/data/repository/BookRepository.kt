package com.example.book.data.repository

import androidx.datastore.preferences.core.Preferences
import com.example.book.data.model.Book
import com.example.book.data.model.ColorPreset
import com.example.book.data.model.History
import com.example.book.data.model.NullableBook
import com.example.book.data.model.SelectableFile
import com.example.book.data.util.CoverImage
import com.example.book.data.util.Resource
import com.example.book.presentation.main.MainState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

import java.io.File

interface BookRepository {

    suspend fun getBooks(
        query: String
    ): List<Book>

    suspend fun getBooksById(
        ids: List<Int>
    ): List<Book>

    suspend fun getBookText(
        textPath: String
    ): List<String>

    suspend fun insertBook(
        book: Book,
        coverImage: CoverImage?,
        text: List<String>
    ): Boolean

    suspend fun updateBooks(
        books: List<Book>
    )

    suspend fun updateBookWithText(
        book: Book,
        text: List<String>
    ): Boolean

    suspend fun updateCoverImageOfBook(
        bookWithOldCover: Book,
        newCoverImage: CoverImage?
    )

    suspend fun deleteBooks(
        books: List<Book>
    )

    suspend fun canResetCover(
        bookId: Int
    ): Boolean

    suspend fun resetCoverImage(bookId: Int): Boolean


    suspend fun <T> retrieveDataFromDataStore(
        key: Preferences.Key<T>,
        defaultValue: T
    ): Flow<T>

    suspend fun <T> putDataToDataStore(
        key: Preferences.Key<T>,
        value: T
    )


    suspend fun getAllSettings(scope: CoroutineScope): MainState

    suspend fun getFilesFromDevice(query: String = ""): List<SelectableFile>

    suspend fun getBooksFromFiles(files: List<File>): List<NullableBook>


    suspend fun insertHistory(history: List<History>)

    suspend fun getHistory(): Flow<Resource<List<History>>>

    suspend fun getLatestBookHistory(bookId: Int): History?

    suspend fun deleteWholeHistory()

    suspend fun deleteBookHistory(bookId: Int)

    suspend fun deleteHistory(
        history: List<History>
    )





    suspend fun updateColorPreset(colorPreset: ColorPreset)

    suspend fun selectColorPreset(colorPreset: ColorPreset)

    suspend fun getColorPresets(): List<ColorPreset>

    suspend fun reorderColorPresets(orderedColorPresets: List<ColorPreset>)

    suspend fun deleteColorPreset(colorPreset: ColorPreset)


    suspend fun updateFavoriteDirectory(path: String)
}