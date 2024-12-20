package com.example.book.data.parser


import com.example.book.R
import com.example.book.data.parser.epub.EpubTextParser
import com.example.book.data.parser.fb2.Fb2TextParser
import com.example.book.data.parser.pdf.PdfTextParser
import com.example.book.data.parser.txt.TxtTextParser
import com.example.book.data.util.Resource
import com.example.book.data.util.UIText

import java.io.File
import javax.inject.Inject

class TextParserImpl @Inject constructor(
    private val txtTextParser: TxtTextParser,
    private val pdfTextParser: PdfTextParser,
    private val epubTextParser: EpubTextParser,
    private val fb2TextParser: Fb2TextParser
) : TextParser {
    override suspend fun parse(file: File): Resource<List<String>> {
        if (!file.exists()) {
            return Resource.Error(
                UIText.StringResource(R.string.error_something_went_wrong_with_file)
            )
        }

        val fileFormat = ".${file.name.substringAfterLast(".")}".lowercase().trim()

        if (fileFormat == ".pdf") {
            return pdfTextParser.parse(file)
        }

        if (fileFormat == ".epub") {
            return epubTextParser.parse(file)
        }

        if (fileFormat == ".txt") {
            return txtTextParser.parse(file)
        }

        if (fileFormat == ".fb2") {
            return fb2TextParser.parse(file)
        }

        return Resource.Error(UIText.StringResource(R.string.error_wrong_file_format))
    }
}








