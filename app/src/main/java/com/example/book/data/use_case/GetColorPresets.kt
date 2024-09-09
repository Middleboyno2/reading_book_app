package com.example.book.data.use_case


import com.example.book.data.model.ColorPreset
import com.example.book.data.repository.BookRepository
import javax.inject.Inject

class GetColorPresets @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(): List<ColorPreset> {
        return repository.getColorPresets()
    }
}