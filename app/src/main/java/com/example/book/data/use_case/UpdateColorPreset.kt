package com.example.book.data.use_case


import com.example.book.data.model.ColorPreset
import com.example.book.data.repository.BookRepository
import javax.inject.Inject

class UpdateColorPreset @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(colorPreset: ColorPreset) {
        repository.updateColorPreset(colorPreset)
    }
}