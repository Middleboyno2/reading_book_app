package com.example.book.data.use_case

import androidx.datastore.preferences.core.Preferences
import com.example.book.data.repository.BookRepository
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class GetDatastore @Inject constructor(private val repository: BookRepository) {

    suspend fun <T> execute(key: Preferences.Key<T>, defaultValue: T): Flow<T> {
        return repository.retrieveDataFromDataStore(key, defaultValue)
    }
}