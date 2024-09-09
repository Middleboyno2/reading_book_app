package com.example.book.data.use_case


import com.example.book.data.model.SelectableFile
import com.example.book.data.repository.BookRepository
import javax.inject.Inject

class GetFilesFromDevice @Inject constructor(
    private val repository: BookRepository
) {

    suspend fun execute(query: String): List<SelectableFile> {
        return repository.getFilesFromDevice(query)
    }
}