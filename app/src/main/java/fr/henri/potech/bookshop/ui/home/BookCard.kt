package fr.henri.potech.bookshop.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import fr.henri.potech.bookshop.ui.BookCover
import java.math.BigDecimal

@Composable
fun BookCard(book: BookCardState, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        BookCover()
        Text(
            text = book.title,
            fontSize = 12.sp,
            lineHeight = 14.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "$${book.price}",
            fontSize = 10.sp,
            lineHeight = 11.sp
        )
    }
}

data class BookCardState(
    val title: String,
    val price: BigDecimal
)
