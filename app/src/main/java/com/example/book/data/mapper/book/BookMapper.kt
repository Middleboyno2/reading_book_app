package com.example.book.data.mapper.book

import com.example.book.data.dto.BookEntity
import com.example.book.data.model.Book

interface BookMapper {
    suspend fun toBookEntity(book: Book): BookEntity

    suspend fun toBook(bookEntity: BookEntity): Book
}