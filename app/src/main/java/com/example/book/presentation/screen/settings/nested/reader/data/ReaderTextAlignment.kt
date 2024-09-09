package com.example.book.presentation.screen.settings.nested.reader.data

enum class ReaderTextAlignment {
    START, JUSTIFY, CENTER, END
}

fun String.toTextAlignment(): ReaderTextAlignment {
    return ReaderTextAlignment.valueOf(this)
}