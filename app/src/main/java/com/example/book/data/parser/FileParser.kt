package com.example.book.data.parser


import com.example.book.data.model.Book
import com.example.book.data.util.CoverImage
import java.io.File


interface FileParser {

    suspend fun parse(file: File): Pair<Book, CoverImage?>?
}