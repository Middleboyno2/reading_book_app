package com.example.book.data.use_case


import com.example.book.data.model.History
import com.example.book.data.repository.BookRepository
import javax.inject.Inject

class GetLatestHistory @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(bookId: Int): History? {
        return repository.getLatestBookHistory(bookId)
    }
}