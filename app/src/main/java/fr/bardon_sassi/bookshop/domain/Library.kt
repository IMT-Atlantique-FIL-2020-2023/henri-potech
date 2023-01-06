package fr.bardon_sassi.bookshop.domain

import fr.bardon_sassi.bookshop.data.remote.HenriPotierApi
import java.net.URL

data class Library(val books: List<Book>) {
    companion object {
        suspend fun fetchBooks(): Library {
            return Library(HenriPotierApi.client.getBooks().map { book ->
                Book(
                    coverUrl = URL(book.cover),
                    title = book.title,
                    isbn = book.isbn,
                    synopsis = book.synopsis.joinToString(""),
                    price = book.price
                )
            })
        }
    }
}

