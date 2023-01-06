package fr.henri.potech.bookshop.ui.cart

import androidx.annotation.MainThread
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.henri.potech.bookshop.domain.Book
import fr.henri.potech.bookshop.domain.Cart
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.net.URL

class CartViewModel : ViewModel() {
    val cart = MutableStateFlow(Cart())
    init {
        viewModelScope.launch {
            for (i in 1..10) {
                delay(500)
                cart.update {
                    it.copy(
                        books = it.books + Book(
                            coverUrl = URL("https://placekitten.com/200/300"),
                            title = "Title $i",
                            isbn = "$i",
                            synopsis = "Synopsis $i",
                            price = BigDecimal(10 * i % 40 + 2),
                        )
                    )
                }
            }
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