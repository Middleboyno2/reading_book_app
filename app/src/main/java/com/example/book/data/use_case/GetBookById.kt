package com.example.book.data.use_case


import com.example.book.data.model.Book
import com.example.book.data.repository.BookRepository
import javax.inject.Inject

class GetBookById @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(id: Int): Book? {
        return repository.getBooksById(listOf(id)).firstOrNull()
    }
}