package com.example.book.data.parser


import com.example.book.data.util.Resource
import java.io.File

interface TextParser {
    suspend fun parse(file: File): Resource<List<String>>
}