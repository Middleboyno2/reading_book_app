package com.example.book.data.use_case

import com.example.book.data.model.History
import com.example.book.data.repository.BookRepository
import com.example.book.data.util.Resource
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetHistory @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(): Flow<Resource<List<History>>> {
        return repository.getHistory()
    }
}