package fr.henri.potech.bookshop.ui

import androidx.compose.runtime.Composable
import fr.henri.potech.bookshop.ui.cart.BottomCartSheetScaffold
import fr.henri.potech.bookshop.ui.home.BookList
import fr.henri.potech.bookshop.ui.theme.BookShopTheme

@Composable
fun HenriPotechApp() {
//    val coroutineScope = rememberCoroutineScope()

//    var books: List<BookCardState> = remember { mutableStateListOf() }

//    // TODO: Call API to load books
//    coroutineScope.launch {
//        println("Faking network call…")
//        delay(1000L) // delay for 1 second (1000 milliseconds)
//        books = (1..50).toList()
//            .map { i -> BookCardState("Title $i", BigDecimal(10 * i % 40 + 2)) }
//        println("Books loaded")
//    }

    BookShopTheme {
        BottomCartSheetScaffold {
            BookList()
        }
    }
}
