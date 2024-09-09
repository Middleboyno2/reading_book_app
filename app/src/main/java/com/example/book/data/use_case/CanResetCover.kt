package com.example.book.data.use_case


import com.example.book.data.repository.BookRepository
import javax.inject.Inject

class CanResetCover @Inject constructor(
    private val repository: BookRepository
) {

    suspend fun execute(bookId: Int): Boolean {
        return repository.canResetCover(bookId)
    }
}