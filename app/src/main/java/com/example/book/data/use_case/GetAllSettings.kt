package com.example.book.data.use_case

import com.example.book.data.repository.BookRepository
import kotlinx.coroutines.CoroutineScope

import com.example.book.presentation.main.MainState
import javax.inject.Inject

class GetAllSettings @Inject constructor(
    private val repository: BookRepository
) {

    suspend fun execute(scope: CoroutineScope): MainState {
        return repository.getAllSettings(scope)
    }
}