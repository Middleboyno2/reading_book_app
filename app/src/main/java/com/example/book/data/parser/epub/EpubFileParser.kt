package com.example.book.data.parser.epub

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import com.example.book.data.model.Book
import com.example.book.data.model.Category
import com.example.book.data.util.CoverImage
import com.example.book.data.util.UIText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import com.example.book.data.parser.FileParser

import java.io.File
import java.util.zip.ZipFile
import javax.inject.Inject

class EpubFileParser @Inject constructor() : FileParser {

    override suspend fun parse(file: File): Pair<Book, CoverImage?>? {
        if (!file.name.endsWith(".epub", true) || !file.exists()) {
            return null
        }

        try {
            var book: Pair<Book, CoverImage?>? = null

            withContext(Dispatchers.IO) {
                ZipFile(file).use { zip ->
                    val opfEntry = zip.entries().asSequence().find { entry ->
                        entry.name.endsWith(".opf")
                    } ?: return@withContext

                    val opfContent = zip
                        .getInputStream(opfEntry)
                        .bufferedReader()
                        .use { it.readText() }
                    val document = Jsoup.parse(opfContent)

                    val title = document.select("metadata > dc|title").text().trim()
                    val author = UIText.StringValue(
                        document.select("metadata > dc|creator").text().trim()
                    )
                    val description = Jsoup.parse(
                        document.select("metadata > dc|description").text()
                    ).text()

                    var coverImagePath: String? = null

                    val coverId = document
                        .select("metadata > meta[name=cover]")
                        .attr("content")
                    if (coverId.isNotBlank()) {
                        coverImagePath = document
                            .select("manifest > item[id=$coverId]")
                            .attr("href")
                    }

                    if (coverImagePath.isNullOrBlank()) {
                        coverImagePath = document
                            .select("manifest > item[media-type*=image]")
                            .firstOrNull()?.attr("href")
                    }

                    book = Book(
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
                    ) to extractCoverImageBitmap(file, coverImagePath)
                }
            }
            return book
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }
    }
}

private fun extractCoverImageBitmap(file: File, coverImagePath: String?): Bitmap? {
    if (coverImagePath.isNullOrBlank()) {
        return null
    }

    ZipFile(file).use { zip ->
        zip.entries().asSequence().forEach { entry ->
            if (entry.name.endsWith(coverImagePath)) {
                val imageBytes = zip.getInputStream(entry).readBytes()
                return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            }
        }
    }

    return null
}