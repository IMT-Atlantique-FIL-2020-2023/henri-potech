package fr.henri.potech.bookshop.data.books.impl

import fr.henri.potech.bookshop.data.books.BooksRepository
import fr.henri.potech.bookshop.domain.Book
import fr.henri.potech.bookshop.domain.Library
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

/**
 * Implementation of [BooksRepository] that returns a hardcoded list of
 * books with resources after some delay in a background thread.
 */
class FakeBooksRepository : BooksRepository {
    override suspend fun getBook(bookISBN: String?): Result<Book> {
        return withContext(Dispatchers.IO) {
            val book = hardcodedLibrary.books.find { it.isbn == bookISBN }
            if (book == null) {
                Result.failure(IllegalArgumentException("Book not found"))
            } else {
                Result.success(book)
            }
        }
    }

    override suspend fun getLibrary(): Result<Library> {
        return withContext(Dispatchers.IO) {
            delay(800) // pretend we're on a slow network
            if (shouldRandomlyFail()) {
                Result.failure(IllegalStateException())
            } else {
                Result.success(hardcodedLibrary)
            }
        }
    }

    // Used to drive "random" failure in a predictable pattern, making the first request always
    // succeed
    private var requestCount = 0

    /**
     * Randomly fail some loads to simulate a real network.
     *
     * This will fail deterministically every 5 requests
     */
    private fun shouldRandomlyFail(): Boolean = ++requestCount % 5 == 0
}
