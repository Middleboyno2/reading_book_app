package com.example.book.data.repository

import com.example.book.data.data_store.DataStore
import com.example.book.data.data_store.DataStoreImpl
import com.example.book.data.mapper.book.BookMapper
import com.example.book.data.mapper.book.BookMapperImpl
import com.example.book.data.mapper.color_preset.ColorPresetMapper
import com.example.book.data.mapper.color_preset.ColorPresetMapperImpl
import com.example.book.data.mapper.history.HistoryMapper
import com.example.book.data.mapper.history.HistoryMapperImpl
import com.example.book.data.parser.FileParser
import com.example.book.data.parser.FileParserImpl
import com.example.book.data.parser.TextParser
import com.example.book.data.parser.TextParserImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDataStore(
        dataStoreImpl: DataStoreImpl
    ): DataStore

    @Binds
    @Singleton
    abstract fun bindBookRepository(
        bookRepositoryImpl: BookRepositoryImpl
    ): BookRepository

    @Binds
    @Singleton
    abstract fun bindBookMapper(
        bookMapperImpl: BookMapperImpl
    ): BookMapper

    @Binds
    @Singleton
    abstract fun bindHistoryMapper(
        historyMapperImpl: HistoryMapperImpl
    ): HistoryMapper

    @Binds
    @Singleton
    abstract fun bindColorPresetMapper(
        colorPresetMapperImpl: ColorPresetMapperImpl
    ): ColorPresetMapper

    @Binds
    @Singleton
    abstract fun bindFileParser(
        fileParserImpl: FileParserImpl
    ): FileParser

    @Binds
    @Singleton
    abstract fun bindTextParser(
        textParserImpl: TextParserImpl
    ): TextParser

}