package fr.henri.potech.bookshop.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.math.BigDecimal
import java.net.URL

@Composable
fun BookGrid(
    cardStates: List<BookCardState>,
    onClick: (String) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier.padding(4.dp),
//        contentPadding = PaddingValues(4.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        items(cardStates) { cardState ->
            BookCard(
                state = cardState,
                modifier = Modifier
                    .padding(4.dp)
                    .widthIn(max = 200.dp),
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
            BookCardState(
                isbn = "$i",
                coverUrl = URL("https://firebasestorage.googleapis.com/v0/b/henri-potier.appspot.com/o/hp1.jpg?alt=media"),
                title = "Title $i",
                price = BigDecimal(10 * i % 40 + 2),
            )
        }
    BookGrid(books)
}
