package com.example.book.data.use_case


import com.example.book.data.model.Book
import com.example.book.data.repository.BookRepository
import com.example.book.data.util.CoverImage
import javax.inject.Inject

class InsertBook @Inject constructor(private val repository: BookRepository) {
    suspend fun execute(
        book: Book,
        coverImage: CoverImage?,
        text: List<String>
    ): Boolean {
        return repository.insertBook(book, coverImage, text)
    }
}