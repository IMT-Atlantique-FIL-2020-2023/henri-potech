package fr.henri.potech.bookshop.ui.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import fr.henri.potech.bookshop.R
import fr.henri.potech.bookshop.domain.Book
import java.math.BigDecimal
import java.net.URL

@Composable
fun BookGrid(
    books: List<Book>,
    onClick: (book: Book) -> Unit = {},
    contentPadding: PaddingValues = PaddingValues(),
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(
            minSize = dimensionResource(R.dimen.book_grid_min_cell_width)
        ),
        contentPadding = contentPadding,
    ) {
        items(books) { book ->
            BookCard(
                book = book,
                modifier = Modifier
                    .padding(4.dp)
                    .widthIn(max = dimensionResource(R.dimen.book_grid_max_cell_width)),
                onClick = onClick,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BookGridPreview() {
    val books = (1..50).toList()
        .map { i ->
            Book(
                isbn = "$i",
                coverUrl = URL("https://firebasestorage.googleapis.com/v0/b/henri-potier.appspot.com/o/hp1.jpg?alt=media"),
                title = "Title $i",
                synopsis = "",
                price = BigDecimal(10 * i % 40 + 2),
            )
        }
    BookGrid(books)
}
