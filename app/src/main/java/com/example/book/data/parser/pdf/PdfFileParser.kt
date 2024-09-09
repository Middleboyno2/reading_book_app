package com.example.book.data.parser.pdf

import android.app.Application
import com.example.book.R
import com.example.book.data.model.Book
import com.example.book.data.model.Category
import com.example.book.data.util.CoverImage
import com.example.book.data.util.UIText
import com.tom_roush.pdfbox.android.PDFBoxResourceLoader
import com.tom_roush.pdfbox.pdmodel.PDDocument
import com.example.book.data.parser.FileParser

import java.io.File
import javax.inject.Inject

class PdfFileParser @Inject constructor(private val application: Application) : FileParser {

    override suspend fun parse(file: File): Pair<Book, CoverImage?>? {
        if (!file.name.endsWith(".pdf", true) || !file.exists()) {
            return null
        }

        try {
            PDFBoxResourceLoader.init(application)

            val document = PDDocument.load(file)

            val title = document.documentInformation.title ?: file.name.dropLast(4).trim()
            val fileAuthor = document.documentInformation.author
            val author = if (fileAuthor != null) UIText.StringValue(fileAuthor)
            else UIText.StringResource(R.string.unknown_author)
            val description = document.documentInformation.subject ?: null

            document.close()

            return Book(
                title = title,
                author = author,
                description = description,
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