package fr.henri.potech.bookshop.data.books

import fr.henri.potech.bookshop.domain.Book
import fr.henri.potech.bookshop.domain.Library

/**
 * Interface to the Books data layer.
 */
interface BooksRepository {
    suspend fun getBook(bookId: String?): Result<Book>
    suspend fun getLibrary(): Result<Library>
}
