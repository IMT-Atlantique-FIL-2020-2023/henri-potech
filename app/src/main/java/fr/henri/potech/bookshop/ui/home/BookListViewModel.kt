package fr.henri.potech.bookshop.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.math.BigDecimal

data class BookListViewModel(
    var books: List<BookCardState> = (1..10).toList()
        .map { i -> BookCardState("Title $i", BigDecimal(10 * i % 40 + 2)) }
) : ViewModel() {
    init {
        // TODO: Call API to load books
        viewModelScope.launch {
            println("Faking network call…")
            delay(1000L) // delay for 1 second (1000 milliseconds)
            books = (1..50).toList()
                .map { i -> BookCardState("Title $i", BigDecimal(10 * i % 40 + 2)) }
            println("Books loaded")
        }
    }
}
