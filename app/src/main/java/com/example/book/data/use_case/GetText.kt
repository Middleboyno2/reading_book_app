package com.example.book.data.use_case


import com.example.book.data.repository.BookRepository
import javax.inject.Inject

class GetText @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(textPath: String): List<String> {
        return repository.getBookText(textPath = textPath)
    }
}