package fr.bardon_sassi.bookshop.ui

import androidx.compose.runtime.Composable
import fr.bardon_sassi.bookshop.ui.cart.BottomCartSheetScaffold
import fr.bardon_sassi.bookshop.ui.home.BookList
import fr.bardon_sassi.bookshop.ui.theme.BookShopTheme

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
        BottomCartSheetScaffold { sheetPadding ->
            BookList(contentPadding = sheetPadding)
        }
    }
}
