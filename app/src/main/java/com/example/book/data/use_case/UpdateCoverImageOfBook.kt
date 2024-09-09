package com.example.book.data.use_case


import com.example.book.data.model.Book
import com.example.book.data.repository.BookRepository
import com.example.book.data.util.CoverImage
import javax.inject.Inject

class UpdateCoverImageOfBook @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(bookWithOldCover: Book, newCoverImage: CoverImage?) {
        repository.updateCoverImageOfBook(bookWithOldCover, newCoverImage)
    }
}