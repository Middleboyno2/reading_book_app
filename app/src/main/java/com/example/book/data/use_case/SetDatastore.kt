package com.example.book.data.use_case

import androidx.datastore.preferences.core.Preferences
import com.example.book.data.repository.BookRepository

import javax.inject.Inject

class SetDatastore @Inject constructor(private val repository: BookRepository) {

    suspend fun <T> execute(key: Preferences.Key<T>, value: T) {
        repository.putDataToDataStore(key, value)
    }
}