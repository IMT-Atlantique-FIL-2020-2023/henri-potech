package fr.henri.potech.bookshop.ui.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.henri.potech.bookshop.domain.Book
import fr.henri.potech.bookshop.ui.BookCover
import java.math.BigDecimal
import java.net.URL

@Composable
fun BookCard(
    state: BookCardState,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = {}
) {
    Box(
        modifier = modifier
            .clickable { onClick(state.isbn) },
    ) {
        Column(
            modifier = Modifier
                .padding(4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            BookCover(
                url = state.coverUrl,
            )
            Text(
                text = state.title,
                fontSize = 12.sp,
                lineHeight = 14.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "$${state.price}",
                fontSize = 10.sp,
                lineHeight = 11.sp
            )
        }
    }
}

data class BookCardState(
    val isbn: String,
    val coverUrl: URL,
    val title: String,
    val price: BigDecimal
) {
    companion object {
        fun from(book: Book) : BookCardState {
            return BookCardState(
                isbn = book.isbn,
                coverUrl = book.coverUrl,
                title = book.title,
                price = book.price,
            )
        }
    }
}
