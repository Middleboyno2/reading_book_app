package com.example.book.data.use_case


import com.example.book.data.repository.BookRepository
import javax.inject.Inject

class UpdateFavoriteDirectory @Inject constructor(private val repository: BookRepository) {
    suspend fun execute(path: String) {
        return repository.updateFavoriteDirectory(path)
    }
}