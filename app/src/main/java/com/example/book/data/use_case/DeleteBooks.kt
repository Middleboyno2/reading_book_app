package ua.acclorite.book_story.domain.use_case

import com.example.book.data.model.Book
import com.example.book.data.repository.BookRepository

import javax.inject.Inject

class DeleteBooks @Inject constructor(private val repository: BookRepository) {

    suspend fun execute(books: List<Book>) {
        repository.deleteBooks(books)
        books.forEach {
            repository.deleteBookHistory(it.id)
        }
    }
}