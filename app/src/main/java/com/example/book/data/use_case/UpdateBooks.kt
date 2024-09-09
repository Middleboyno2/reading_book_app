package com.example.book.data.use_case

import com.example.book.data.model.Book
import com.example.book.data.repository.BookRepository
import javax.inject.Inject

class UpdateBooks @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(books: List<Book>) {
        repository.updateBooks(books)
    }
}