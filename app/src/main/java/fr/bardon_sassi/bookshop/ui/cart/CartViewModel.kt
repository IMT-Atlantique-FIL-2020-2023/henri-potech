package fr.bardon_sassi.bookshop.ui.cart

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import fr.bardon_sassi.bookshop.domain.Book
import fr.bardon_sassi.bookshop.domain.Cart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class CartViewModel : ViewModel() {
    val cart = MutableStateFlow(Cart())

    fun addToCart(book: Book) {
        cart.update {
            it.copy(
                books = it.books + book.copy(uuid = book.uuid + Math.random().toString()),
            )
        }
    }

    companion object {
        private lateinit var instance: CartViewModel

        @MainThread
        fun getInstance(): CartViewModel {
            instance = if (::instance.isInitialized) instance else CartViewModel()
            return instance
        }
    }
}