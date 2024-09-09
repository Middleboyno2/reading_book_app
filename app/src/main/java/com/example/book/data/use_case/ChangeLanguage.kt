package com.example.book.data.use_case

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.example.book.data.repository.BookRepository
import com.example.book.data.util.DataStoreConstants

import javax.inject.Inject

class ChangeLanguage @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(language: String) {
        val appLocale = LocaleListCompat.forLanguageTags(language)
        AppCompatDelegate.setApplicationLocales(appLocale)

        repository.putDataToDataStore(
            DataStoreConstants.LANGUAGE,
            language
        )
    }
}