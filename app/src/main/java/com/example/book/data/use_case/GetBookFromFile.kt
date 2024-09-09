package com.example.book.data.use_case


import com.example.book.data.model.NullableBook
import com.example.book.data.repository.BookRepository
import java.io.File
import javax.inject.Inject

class GetBookFromFile @Inject constructor(
    private val repository: BookRepository
) {

    suspend fun execute(file: File): NullableBook {
        return repository.getBooksFromFiles(listOf(file)).first()
    }
}