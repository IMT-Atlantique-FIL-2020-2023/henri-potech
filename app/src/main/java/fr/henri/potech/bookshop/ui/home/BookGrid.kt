package fr.henri.potech.bookshop.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.henri.potech.bookshop.domain.Book
import java.math.BigDecimal
import java.net.URL

@Composable
fun BookGrid(
    books: List<Book>,
    onBookTapped: (bookISBN: String) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        contentPadding = PaddingValues(4.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        items(books) { book ->
            BookCard(book = book, modifier = Modifier.padding(8.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookGridPreview() {
    val books = (1..50).toList()
        .map { i ->
            Book(
                coverUrl = URL("https://firebasestorage.googleapis.com/v0/b/henri-potier.appspot.com/o/hp1.jpg?alt=media"),
                title = "Title $i",
                isbn = "$i",
                synopsis = "Bla bla bla",
                price = BigDecimal(10 * i % 40 + 2)
            )
        }
    BookGrid(books)
}