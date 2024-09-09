package com.example.book.data.use_case


import com.example.book.data.model.History
import com.example.book.data.repository.BookRepository
import javax.inject.Inject

class DeleteHistory @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(history: List<History>) {
        repository.deleteHistory(history)
    }
}