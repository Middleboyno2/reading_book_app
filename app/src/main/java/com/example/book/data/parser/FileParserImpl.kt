package com.example.book.data.parser

import com.example.book.data.model.Book
import com.example.book.data.parser.epub.EpubFileParser
import com.example.book.data.parser.fb2.Fb2FileParser
import com.example.book.data.parser.pdf.PdfFileParser
import com.example.book.data.parser.txt.TxtFileParser
import com.example.book.data.util.CoverImage

import java.io.File
import javax.inject.Inject

class FileParserImpl @Inject constructor(
    private val txtFileParser: TxtFileParser,
    private val pdfFileParser: PdfFileParser,
    private val epubFileParser: EpubFileParser,
    private val fb2FileParser: Fb2FileParser
) : FileParser {
    override suspend fun parse(file: File): Pair<Book, CoverImage?>? {
        if (!file.exists()) {
            return null
        }

        val fileFormat = ".${file.name.substringAfterLast(".")}".lowercase().trim()

        if (fileFormat == ".pdf") {
            return pdfFileParser.parse(file)
        }

        if (fileFormat == ".epub") {
            return epubFileParser.parse(file)
        }

        if (fileFormat == ".txt") {
            return txtFileParser.parse(file)
        }

        if (fileFormat == ".fb2") {
            return fb2FileParser.parse(file)
        }

        return null
    }
}








