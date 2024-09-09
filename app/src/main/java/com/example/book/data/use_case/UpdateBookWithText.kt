package com.example.book.data.use_case


import com.example.book.data.model.Book
import com.example.book.data.repository.BookRepository
import javax.inject.Inject

class UpdateBookWithText @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(book: Book, text: List<String>): Boolean {
        return repository.updateBookWithText(book, text)
    }
}