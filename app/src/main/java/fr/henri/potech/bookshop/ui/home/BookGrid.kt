package fr.henri.potech.bookshop.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import fr.henri.potech.bookshop.domain.Book

@Composable
fun BookGrid(
    books: List<Book>,
    onClick: (Book) -> Unit = {},
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 128.dp),
        modifier = Modifier.padding(4.dp),
//        contentPadding = PaddingValues(4.dp),
//        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        items(books) { book ->
            BookCard(
                book,
                modifier = Modifier
                    .padding(4.dp)
                    .widthIn(max = 200.dp),
                onClick = onClick,
            )
        }
    }
}
