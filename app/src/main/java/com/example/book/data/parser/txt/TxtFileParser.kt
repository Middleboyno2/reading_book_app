package com.example.book.data.parser.txt


import com.example.book.R
import com.example.book.data.model.Book
import com.example.book.data.model.Category
import com.example.book.data.util.CoverImage
import com.example.book.data.util.UIText
import com.example.book.data.parser.FileParser
import java.io.File
import javax.inject.Inject

class TxtFileParser @Inject constructor() : FileParser {

    override suspend fun parse(file: File): Pair<Book, CoverImage?>? {
        if (!file.name.endsWith(".txt", true) || !file.exists()) {
            return null
        }

        try {
            val title = file.name.trim().dropLast(4)
            val author = UIText.StringResource(R.string.unknown_author)

            return Book(
                title = title,
                author = author,
                description = null,
                textPath = "",
                scrollIndex = 0,
                scrollOffset = 0,
                progress = 0f,
                filePath = file.path,
                lastOpened = null,
                category = Category.entries[0],
                coverImage = null
            ) to null
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}